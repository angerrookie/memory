package com.pubinfo.memory.service;


import com.pubinfo.memory.dto.ResponseReturn;
import com.pubinfo.memory.entity.FileDocument;
import com.pubinfo.memory.entity.FileModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface IUploadService {

    /**
     * 功能描述: 保存文件
     * @Param: [file]
     * @Return: com.pubinfo.memory.dto.ResponseReturn
     * @Author: Administrator
     * @Date: 2020/4/29 8:22
     */
    ResponseReturn saveFile(MultipartFile[] file);

    String uploadFileToGridFS(InputStream in , String contentType);

    FileDocument saveFile(FileDocument file);


    /**
     * 功能描述: 根据id查询文件
     * @Param: [id]
     * @Return: byte[]
     * @Author: Administrator
     * @Date: 2020/4/29 8:22
     */
    byte[] getFileById(String id);

    /**
     * 功能描述: 返回文件列表
     * @Param: []
     * @Return: com.pubinfo.memory.dto.ResponseReturn
     * @Author: Administrator
     * @Date: 2020/4/29 8:22
     */
    ResponseReturn list();
}
