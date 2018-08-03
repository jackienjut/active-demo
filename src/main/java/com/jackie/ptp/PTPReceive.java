package com.jackie.ptp;

import com.jackie.Config;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PTPReceive {

    private String userName = Config.USER_NAME;
    private String password = Config.PASSWORD;
    private String brokerUrl = Config.BROKE_URL;

    private ConnectionFactory factory;
    private Connection connection;

    private Session session;

    private Destination destination;
    private MessageConsumer consumer;

    public static void main(String[] args) {
        PTPReceive receive = new PTPReceive();
       receive.start();
    }

    public void start() {
        try {
            factory = new ActiveMQConnectionFactory(userName, password, brokerUrl);
            connection = factory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            destination = session.createQueue("jackie-msg");
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        String str = ((TextMessage) message).getText();
                        System.out.println(str);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            consumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
