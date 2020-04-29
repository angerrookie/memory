package com.pubinfo.memory.repository;


import com.pubinfo.memory.entity.FileModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends MongoRepository<FileModel,String> {

}
