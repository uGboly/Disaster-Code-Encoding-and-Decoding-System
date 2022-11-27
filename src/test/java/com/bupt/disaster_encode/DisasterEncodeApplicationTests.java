package com.bupt.disaster_encode;

import com.bupt.disaster_encode.domain.DisasterManagement;
import com.bupt.disaster_encode.service.EncodingService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class DisasterEncodeApplicationTests {

    @Autowired
    EncodingService encodingService;

    @Test
    void contextLoads() {
    }

    @Test
    void decode() throws Exception {
        String disasterCode = "320509107207202105220204001010302001";
        DisasterManagement disasterManagement =  encodingService.decoding(disasterCode);
        System.out.println(disasterManagement);
    }

}
