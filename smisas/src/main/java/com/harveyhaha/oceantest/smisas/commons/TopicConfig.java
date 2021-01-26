package com.harveyhaha.oceantest.smisas.commons;

import com.harveyhaha.oceantest.common.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {
    /**
     * 用户管理队列
     *
     * @return
     */
    @Bean
    public Queue userManageQueue() {
        return new Queue(Constants.USER_MANAGE_TOPIC);
    }

    @Bean
    public Queue userRegisterQueue() {
        return new Queue(Constants.USER_MANAGE_REGISTER_TOPIC);
    }

    /**
     * 船联网应用管理服务 交换机
     *
     * @return
     */
    @Bean
    TopicExchange smisasExchange() {
        return new TopicExchange(Constants.SMISAS_EXCHANGE);
    }

    @Bean
    Binding bindingUserManageExchangeMessage() {
        return BindingBuilder.bind(userManageQueue()).to(smisasExchange()).with(Constants.USER_MANAGE_TOPIC);
    }

    @Bean
    Binding bindingUserRegisterExchangeMessage() {
        return BindingBuilder.bind(userRegisterQueue()).to(smisasExchange()).with(Constants.USER_MANAGE_REGISTER_TOPIC);
    }

//    @Bean
//    public ConnectionFactory connectionFactory(){
//        CachingConnectionFactory factory = new CachingConnectionFactory();
////        factory.setUri("amqp://zhihao.miao:123456@192.168.1.131:5672");
//        return factory;
//    }
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        //SimpleRabbitListenerContainerFactory发现消息中有content_type有text就会默认将其转换成string类型的
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
