package com.baiten.activemq;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by windy on 2018/3/26.
 */
@Service
public class AMQSenderServiceImpl implements AmqSenderService{

    private static final Logger logger = LoggerFactory.getLogger(AMQSenderServiceImpl.class);
    Gson gson = new Gson();

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    //目的地队列的明证，我们要向这个队列发送消息
    @Resource(name = "destinationTopic")
    private Destination destination;

    public void sendMsg(UserEntity user) {
        final String msg = gson.toJson(user);
        try {
            logger.info("将要向队列{}发送的消息msg:{}", destination, msg);
            jmsTemplate.send(destination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(msg);
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("向队列{}发送消息失败，消息为：{}", destination, msg);
        }

    }
}
