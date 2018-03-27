package com.baiten.test;

import com.baiten.activemq.AmqSenderService;
import com.baiten.activemq.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by windy on 2018/3/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring-config.xml")
public class TestActiveMq {

    @Autowired
    AmqSenderService amqSenderService;

    @Test
    public void test(){

        UserEntity userEntity = new UserEntity();
        userEntity.setName("madea");
        userEntity.setAddress("中国常州");
        amqSenderService.sendMsg(userEntity);
    }
}
