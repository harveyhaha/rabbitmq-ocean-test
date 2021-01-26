package com.harveyhaha.oceantest.smisas.usermanage;

import com.harveyhaha.oceantest.smisas.MineConstants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = MineConstants.USER_MANAGE_REGISTER_TOPIC)
public class TopicUserRegisterReceiver {
    @RabbitHandler
    public void handlerMessage(String message) {
        System.out.println("TopicUserRegisterReceiver handlerMessage 消费者收到消息  : " + message);
    }
//    @RabbitHandler
//    public void onMessage(Map testMessage) {
//        System.out.println("TopicUserRegisterReceiver process 消费者收到消息  : " + testMessage.toString());
//    }
//    @RabbitHandler
//    public void handlerMessage(Message message) {
//        System.out.println("TopicUserRegisterReceiver handlerMessage 消费者收到消息  : " + message.getBody());
//    }
    @RabbitHandler
    public void handlerMessage1(byte[] message) {
        System.out.println("TopicUserRegisterReceiver handlerMessage1 消费者收到消息  : " + message);
    }
}