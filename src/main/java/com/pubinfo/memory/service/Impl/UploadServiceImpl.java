package com.pubinfo.memory.service.Impl;


import cn.hutool.core.util.IdUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.pubinfo.memory.dto.ResponseReturn;
import com.pubinfo.memory.entity.FileDocument;
import com.pubinfo.memory.entity.FileModel;
import com.pubinfo.memory.repository.UploadRepository;
import com.pubinfo.memory.service.IUploadService;
import com.pubinfo.memory.utils.FileUtils;
import com.pubinfo.memory.utils.MD5Util;
import com.pubinfo.memory.utils.Type;
import com.sun.org.apache.bcel.internal.classfile.Constant;
import org.bson.Document;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private UploadRepository uploadRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    /**
     * 功能描述: 存储文件
     *
     * @Param: [fileModel]
     * @Return: com.example.demo2.entity.FileModel
     * @Author: Administrator
     * @Date: 2020/4/28 14:37
     */
    @Override
    public ResponseReturn saveFile(MultipartFile[] files) {
        //file的校验
        try {
            Map<String, Object> objectMap = check(files);
            //小文件集合  可以一次性插入
            List<FileModel> bsons = (List<FileModel>) objectMap.get("bsons");
            if (bsons.size() != 0) {
                uploadRepository.saveAll(bsons);
            }
            //大文件集合  使用GridFS存储
            List<MultipartFile> gridfs = (List<MultipartFile>) objectMap.get("gridfs");
            ResponseReturn responseReturn = saveByGridFS(gridfs);

            return ResponseReturn.addSuccess(responseReturn);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return ResponseReturn.unknownError();
    }

    public ResponseReturn saveByGridFS(List<MultipartFile> files) {
        int size = files.size();
        InputStream in = null;
        for (int i = 0; i < size; i++) {
            try {
                in = files.get(i).getInputStream();
                FileDocument fileDocument = new FileDocument();
                fileDocument.setSuffix("test");

                String gridfsId = saveFiles(in, files.get(i).getOriginalFilename(), fileDocument);
                //大文件管理
                FileDocument fileDocument1 = saveFile(files.get(i), gridfsId);

                return ResponseReturn.addSuccess(fileDocument1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResponseReturn.unknownError();
    }

    /**
     * 功能描述: GridFS数据存储：所有图大于16M的文件
     *
     * @Param: [fileModel]
     * @Return: com.example.demo2.dto.ResponseReturn
     * @Author: Administrator
     * @Date: 2020/4/28 14:38
     */
    public String saveFiles(InputStream in, String fileName, Object metadata) {


        String gridfsId = IdUtil.simpleUUID();
        //将文件存储进GridFS中
        gridFsTemplate.store(in, gridfsId, fileName, metadata);

        return gridfsId;
    }


    /**
     * 上传文件到Mongodb的GridFs中
     *
     * @param in
     * @param contentType
     * @return
     */
    @Override
    public String uploadFileToGridFS(InputStream in, String contentType) {
        String gridfsId = IdUtil.simpleUUID();
        //将文件存储进GridFS中
        gridFsTemplate.store(in, gridfsId, contentType);
        return gridfsId;
    }

    @Override
    public FileDocument saveFile(FileDocument file) {
        file = mongoTemplate.save(file);
        return file;
    }


    public FileDocument saveFile(MultipartFile file,String gridfsId) throws IOException, NoSuchAlgorithmException {

        FileDocument fileDocument = new FileDocument();

        fileDocument.setName(file.getOriginalFilename());
        fileDocument.setSize(file.getSize());
        fileDocument.setContentType(file.getContentType());
        fileDocument.setUploadDate(new Date());
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        fileDocument.setSuffix(suffix);
        fileDocument.setMd5(MD5Util.getMD5(file.getInputStream()));
        fileDocument.setGridfsId(gridfsId);

        FileDocument save = mongoTemplate.save(fileDocument);

        return save;
    }

    @Override
    public byte[] getFileById(String id) {

        FileModel byId = mongoTemplate.findById(id, FileModel.class);
        System.out.println("------->根据id查询"+byId.toString());
        return null;
    }

    @Override
    public ResponseReturn list() {
        List<FileModel> all = uploadRepository.findAll();
        return ResponseReturn.addSuccess(all);
    }

    public Map<String, Object> check(MultipartFile[] files) throws IOException, NoSuchAlgorithmException {


        Map<String, Object> map = new HashMap<>();
        //存储小文件
        List<FileModel> bsons = new ArrayList<>();
        //存储大文件
        List<MultipartFile> gridfs = new ArrayList<>();

        int size = files.length;
        for (int k = 0; k < size; k++) {
            //判断是大文件还是小文件  size小于...
            boolean compare = FileUtils.compare(files[k].getSize(), 12, Type.KB);
            if (compare) {
                //小文件
                String name = files[k].getOriginalFilename();
                String contentType = files[k].getContentType();
                String suffix = name.substring(name.lastIndexOf("."));
                long length = files[k].getSize();
                Binary content = new Binary(files[k].getBytes());

                FileModel fileModel = new FileModel();

                fileModel.setName(name);
                fileModel.setContent(content);
                fileModel.setContentType(contentType);
                fileModel.setSuffix(suffix);
                fileModel.setSize(size);
                fileModel.setUploadDate(new Date());
                fileModel.setMd5(MD5Util.getMD5(files[k].getInputStream()));

                bsons.add(fileModel);
            } else {
                gridfs.add(files[k]);
            }
        }
        map.put("bsons", bsons);
        map.put("gridfs", gridfs);

        return map;
    }
}
