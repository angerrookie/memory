package com.pubinfo.memory.service.Impl;



import com.pubinfo.memory.dto.ResponseReturn;
import com.pubinfo.memory.entity.FileModel;
import com.pubinfo.memory.repository.UploadRepository;
import com.pubinfo.memory.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;

@Service
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private UploadRepository uploadRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 功能描述: 存储文件
     * @Param: [fileModel]
     * @Return: com.example.demo2.entity.FileModel
     * @Author: Administrator
     * @Date: 2020/4/28 14:37
     */
    @Override
    public ResponseReturn saveFile(MultipartFile[] files) {

        //file的校验
        try {
            Map<String, List<FileModel>> map = check(files);

            saveByBson(map.get("bsons"));

            saveByGridFS(map.get("gridfs"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseReturn.success();
    }

    @Override
    public byte[] getFileById(String id) {

        System.out.println(id);
        String s = "ObjectId("+id+")";
        System.out.println(s);


        Query query1 = Query.query(Criteria.where("_id").is(id));
        FileModel one = (FileModel)mongoTemplate.findOne(query1,FileModel.class);
        System.out.println(one.toString());
        return null;
    }

    @Override
    public ResponseReturn list() {
        List<FileModel> all = uploadRepository.findAll();
        return ResponseReturn.addSuccess(all);
    }


    /**
     * 功能描述: BSON数据存储：所有图片、小于16M的文件
     * @Param: [fileModel]
     * @Return: com.example.demo2.dto.ResponseReturn
     * @Author: Administrator
     * @Date: 2020/4/28 14:38
     */
    public ResponseReturn saveByBson(List<FileModel> fileModels){



        return ResponseReturn.success();
    }

    /**
     * 功能描述: GridFS数据存储：所有图大于16M的文件
     * @Param: [fileModel]
     * @Return: com.example.demo2.dto.ResponseReturn
     * @Author: Administrator
     * @Date: 2020/4/28 14:38
     */
    public ResponseReturn saveByGridFS(List<FileModel> fileModels){

        return ResponseReturn.success();
    }




    public Map<String, List<FileModel>> check(MultipartFile[] files) throws IOException {

        Map<String,List<FileModel>> map = new HashMap<>();
        //存储小文件
        List<FileModel> bsons = new ArrayList<>();
        //存储大文件
        List<FileModel> gridfs = new ArrayList<>();
        FileModel fileModel = null;
        for (MultipartFile file : files) {
                String name = file.getOriginalFilename();
                String contentType = file.getContentType();
                long size = file.getSize();
               //判断是大文件还是小文件
        }
        map.put("bson",bsons);
        map.put("gridfs",gridfs);

        return map;
    }
}
