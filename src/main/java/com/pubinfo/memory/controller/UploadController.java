package com.pubinfo.memory.controller;


import com.pubinfo.memory.dto.ResponseReturn;
import com.pubinfo.memory.entity.FileDocument;
import com.pubinfo.memory.entity.FileModel;
import com.pubinfo.memory.service.IUploadService;
import com.pubinfo.memory.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/file")
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private IUploadService uploadService;

    /**
     * 功能描述:文件存储、接收文件数组
     * @Param: [file, request]
     * @Return: com.pubinfo.memory.dto.ResponseReturn
     * @Author: Administrator
     * @Date: 2020/4/29 8:20
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResponseReturn saveFile(@RequestParam("file")MultipartFile[] file, HttpServletRequest request){

        LOGGER.info(">>>>>>>>>>>>上传文件:");
        LOGGER.info(">>>>>>>>>>>>执行方法开始，文件数量："+file.length);

        return uploadService.saveFile(file);
    }

    /**
     * 功能描述: 根据id查询图片
     * @Param: [id, request]
     * @Return: byte[]
     * @Author: Administrator
     * @Date: 2020/4/29 8:21
     */
    @RequestMapping(value = "/findImg/{id}",method = RequestMethod.GET,produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_GIF_VALUE})
    public byte[] getFile(@PathVariable(value = "id")  String id,HttpServletRequest request){

        LOGGER.info(">>>>>>>>>>>>根据id查询文件:");
        LOGGER.info(">>>>>>>>>>>>执行方法开始，id[]："+id);

        return uploadService.getFileById(id);
    }

    /**
     * 功能描述: 显示文件列表
     * @Param: []
     * @Return: com.pubinfo.memory.dto.ResponseReturn
     * @Author: Administrator
     * @Date: 2020/4/29 8:22
     */
    @RequestMapping(value = "/files",method = RequestMethod.GET)
    public ResponseReturn list(){

        LOGGER.info(">>>>>>>>>>>>文件列表:");
        LOGGER.info(">>>>>>>>>>>>执行方法开始....：");

        return uploadService.list();
    }
}
