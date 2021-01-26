package com.harveyhaha.oceantest.smisas.commons;

import com.harveyhaha.oceantest.smisas.MineConstants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
//@RabbitListener(queues = Constants.USER_MANAGE_TOPIC)
public class UserManageTotalReceiver {

//    @RabbitHandler
//    public void process(Map testMessage) {
//        System.out.println("UserManageTotalReceiver 消费者收到消息  : " + testMessage.toString());
//    }
}