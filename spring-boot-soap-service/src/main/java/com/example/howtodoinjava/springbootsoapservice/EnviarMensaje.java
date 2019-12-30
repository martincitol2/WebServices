package com.example.howtodoinjava.springbootsoapservice;

import com.example.howtodoinjava.SpringBootSoapServiceApplication;
import com.howtodoinjava.xml.school.Client;
import com.howtodoinjava.xml.school.ClientDetailsResponse;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import javax.jms.*;

@Component
public class EnviarMensaje {
    private static final Logger logger = LogManager.getLogger(SpringBootSoapServiceApplication.class);
    ClientDetailsResponse response = new ClientDetailsResponse();

    public ClientDetailsResponse enviarMensaje(Client client) {
        response.setCodigoError("200");
        response.setDescripcion("TODO OK");
        try {

            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("clientes");

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);

            TextMessage message = session.createTextMessage(client.toString());
            producer.send(message);
            logger.info("Mensaje Enviado A La Cola MQ:{ " + message.getText() + "}");
            // Clean up
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
            response.setDescripcion("Fallo Envio Cola MQ");
            response.setCodigoError("500");

        }

        return response;
    }

}
