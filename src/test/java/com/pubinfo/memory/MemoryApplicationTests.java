package com.pubinfo.memory;

import com.pubinfo.memory.dto.ResponseReturn;
import com.pubinfo.memory.entity.FileDocument;
import com.pubinfo.memory.entity.FileModel;
import com.pubinfo.memory.repository.FileDocRepository;
import com.pubinfo.memory.repository.UploadRepository;
import com.pubinfo.memory.utils.FileUtils;
import com.pubinfo.memory.utils.Type;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootTest
class MemoryApplicationTests {

    @Autowired
    FileDocRepository fileDocRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void test(){
        boolean compare = FileUtils.compare(11560, 12, Type.KB);
        System.out.println("---->"+compare);

    }

    @Test
    public void test1(){
        FileDocument byId = fileDocRepository.findById(new ObjectId("5ea93443c3fed1115d4343cc"));
        System.out.println("---->"+byId);

        FileDocument byId1 = mongoTemplate.findById("5ea93443c3fed1115d4343cc", FileDocument.class);
        System.out.println("---->"+byId1.toString());
    }
    @Test
    public void test2(){
        List<FileDocument> all = mongoTemplate.findAll(FileDocument.class);
        System.out.println(all);
    }
}
