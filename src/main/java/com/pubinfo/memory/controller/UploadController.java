package com.pubinfo.memory.controller;


import com.pubinfo.memory.common.dto.ResponseReturn;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/files")
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
    @ResponseBody
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
    @ResponseBody
    @RequestMapping(value = "/filesList",method = RequestMethod.GET)
    public ResponseReturn filesList(){

        LOGGER.info(">>>>>>>>>>>>文件列表:");
        LOGGER.info(">>>>>>>>>>>>执行方法开始....：");

        return uploadService.filesList();
    }

    @RequestMapping(value = "/download/{id}",method = RequestMethod.GET)
    public String test(@PathVariable("id")String id, HttpServletRequest request){
        System.out.println("---get--"+id);
            request.getSession().setAttribute("id",id);
        return "test";
    }

    @RequestMapping(value = "/download/{id}",method = RequestMethod.POST)
    public void test1(@PathVariable("id")String id,HttpServletRequest request){
        System.out.println("---post--"+id);
    }

    /**
     * 功能描述: 添加文件
     * @Param: []
     * @Return: java.lang.String
     * @Author: Administrator
     * @Date: 2020/5/11 2:29
     */
    @GetMapping(value = "/addFile")
    public String toAdd(){
            System.out.println("--------addFile--");
        return "addFile";
    }
}
