package com.pubinfo.memory.repository;


import com.pubinfo.memory.entity.FileDocument;
import com.pubinfo.memory.entity.FileModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDocRepository extends MongoRepository<FileModel,String> {

    FileDocument findById(ObjectId objectId);
}
