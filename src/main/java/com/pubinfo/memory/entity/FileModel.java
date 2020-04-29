package com.pubinfo.memory.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document("comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileModel {

    @Id  // 主键
    private String id;
    private String name; // 文件名称
    private String contentType; // 文件类型
    private String suffix;      // 文件后缀名
    private long size;    //文件大小
    private Date uploadDate; //存储日期
    private String md5;  //md5值
    private Binary content; // 文件内容
    private String path; // 文件路径


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileModel fileModel = (FileModel) o;
        return size == fileModel.size &&
                Objects.equals(id, fileModel.id) &&
                Objects.equals(name, fileModel.name) &&
                Objects.equals(contentType, fileModel.contentType) &&
                Objects.equals(suffix, fileModel.suffix) &&
                Objects.equals(uploadDate, fileModel.uploadDate) &&
                Objects.equals(md5, fileModel.md5) &&
                Objects.equals(content, fileModel.content) &&
                Objects.equals(path, fileModel.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentType, suffix, size, uploadDate, md5, content, path);
    }

    @Override
    public String toString() {
        return "FileModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                ", suffix='" + suffix + '\'' +
                ", size=" + size +
                ", uploadDate=" + uploadDate +
                ", md5='" + md5 + '\'' +
                ", content=" + content +
                ", path='" + path + '\'' +
                '}';
    }
}
