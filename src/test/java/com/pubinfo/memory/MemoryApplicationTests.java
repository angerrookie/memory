package com.pubinfo.memory;

import com.pubinfo.memory.dto.ResponseReturn;
import com.pubinfo.memory.entity.FileModel;
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

@SpringBootTest
class MemoryApplicationTests {


    @Test
    public void test(){
        boolean compare = FileUtils.compare(11560, 12, Type.KB);
        System.out.println("---->"+compare);

    }
}
