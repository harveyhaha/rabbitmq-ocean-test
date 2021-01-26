package com.harveyhaha.oceantest.agency;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.harveyhaha.oceantest.common.Constants;
import com.harveyhaha.oceantest.common.RequestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class SendMessageController {
    private static final Logger logger = LoggerFactory.getLogger(AgencyApplication.class);
    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    /**
     * 用户注册
     * 连接注册服务-->基础服务
     */
    @GetMapping("/sendRegisterUserMessage")
    public String sendRegisterUserMessage() {
        logger.debug("sendRegisterUserMessage _______");
        String messageId = String.valueOf(UUID.randomUUID());
        JSONObject messageJsonData = new JSONObject();
        messageJsonData.put("username", "harvey");
        messageJsonData.put("password", "123456");
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        Message message = MessageBuilder
//                .withBody(messageJsonData.toString().getBytes())
//                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
//                .build();

        RequestBean requestBean = new RequestBean(Constants.USER_MANAGE_REGISTER_TOPIC, messageJsonData);
        String jsonObject = JSON.toJSONString(requestBean);
        logger.debug("注册json:"+jsonObject);
//        rabbitTemplate.convertAndSend(Constants.SMISAS_EXCHANGE, Constants.USER_MANAGE_REGISTER_TOPIC, JSON.toJSON(requestBean));
        rabbitTemplate.convertAndSend(Constants.SMISAS_EXCHANGE, Constants.USER_MANAGE_REGISTER_TOPIC, jsonObject);
        return "ok";
    }

    @GetMapping("/sendRegisterUserMessage2")
    public String sendRegisterUserMessage2() {
//        logger.debug("sendRegisterUserMessage");
        String messageId = String.valueOf(UUID.randomUUID());
        JSONObject data = new JSONObject();
        JSONObject messageJsonData = new JSONObject();
        messageJsonData.put("username", "harvey");
        messageJsonData.put("password", "123456");
        data.put("action", Constants.USER_MANAGE_REGISTER_TOPIC);
        data.put("messageId", messageId);
        data.put("messageData", messageJsonData);
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        data.put("createTime", createTime);
        rabbitTemplate.convertAndSend(Constants.SMISAS_EXCHANGE, Constants.USER_MANAGE_REGISTER_TOPIC, data);
        return "ok";
    }

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", manMap);
        return "ok";
    }

    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", womanMap);
        return "ok";
    }

    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: testFanoutMessage ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return "ok";
    }

    @GetMapping("/TestMessageAck")
    public String TestMessageAck() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: non-existent-exchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
        return "ok";
    }

    @GetMapping("/TestMessageAck2")
    public String TestMessageAck2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: lonelyDirectExchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
        return "ok";
    }
}