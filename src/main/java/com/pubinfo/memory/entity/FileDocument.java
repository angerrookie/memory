package com.pubinfo.memory.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Date;

/**
 * 功能描述: 
 * @Param: 大文件管理、存放基本信息
 * @Return:
 * @Author: Administrator
 * @Date: 2020/4/29 14:38
 */
@Document
public class FileDocument {
    @Id  // 主键
    private String id;
    private String name;        // 文件名称
    private long size;          // 文件大小
    private Date uploadDate;    // 上传时间
    private String md5;         // 文件MD5值
    private byte[] content;     // 文件内容
    private String contentType; // 文件类型
    private String suffix;      // 文件后缀名
    private String description; // 文件描述
    private String gridfsId;    // 大文件管理GridFS的ID

    public FileDocument() {
    }

    public FileDocument(String id, String name, long size, Date uploadDate, String md5, byte[] content, String contentType, String suffix, String description, String gridfsId) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.uploadDate = uploadDate;
        this.md5 = md5;
        this.content = content;
        this.contentType = contentType;
        this.suffix = suffix;
        this.description = description;
        this.gridfsId = gridfsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGridfsId() {
        return gridfsId;
    }

    public void setGridfsId(String gridfsId) {
        this.gridfsId = gridfsId;
    }

    @Override
    public String toString() {
        return "FileDocument{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", uploadDate=" + uploadDate +
                ", md5='" + md5 + '\'' +
                ", content=" + Arrays.toString(content) +
                ", contentType='" + contentType + '\'' +
                ", suffix='" + suffix + '\'' +
                ", description='" + description + '\'' +
                ", gridfsId='" + gridfsId + '\'' +
                '}';
    }
}