package com.jackie.ptp;

import com.jackie.Config;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PTPSend {

    private String userName = Config.USER_NAME;
    private String password = Config.PASSWORD;
    private String brokerUrl = Config.BROKE_URL;

    private ConnectionFactory factory;
    private Connection connection;
    private Session session;

    private Destination destination;
    private MessageProducer producer;

    public static void main(String[] args) {
        PTPSend send = new PTPSend();
        send.start();
    }

    public void start() {
        try {
            factory = new ActiveMQConnectionFactory(userName, password, brokerUrl);
            connection = factory.createConnection();

            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("jackie-msg");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            TextMessage textMessage = session.createTextMessage("this is test 123 123 123");
            for (int i = 0; i < 100; i++) {
                producer.send(textMessage);
            }

            System.out.println("send message success");
            producer.close();
         } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}

