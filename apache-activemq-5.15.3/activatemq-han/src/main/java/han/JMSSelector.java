package han;

/**
 * Created by han on 2018/5/8.
 */
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;


public class JMSSelector  {


    public static void main(String[] args) throws Exception {
        String user = env("ACTIVEMQ_USER", "admin");
        String password = env("ACTIVEMQ_PASSWORD", "admin");
        String host = env("ACTIVEMQ_HOST", "localhost");
        int port = Integer.parseInt(env("ACTIVEMQ_PORT", "5672"));
        String destination = arg(args, 0, "topic://han");

        //连接地址
        String brokerURL = "tcp://127.0.0.1:61616";

        ConnectionFactory factory = new ActiveMQConnectionFactory( user, password, brokerURL);
        Destination dest = null;
        if( destination.startsWith("topic://") ) {
            dest = new ActiveMQTopic(destination);
        } else {
            dest = new ActiveMQQueue(destination);
        }

        Connection connection = factory.createConnection(user, password);
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        createComsumer(dest, session, "receiver = 'A'", "ConsumerA");
        createComsumer(dest, session, "receiver = 'B'", "ConsumerB");
        createComsumer(dest, session, "receiver = 'A' OR receiver = 'B' ", "ConsumerAB");
        createComsumer(dest, session, "", "ConsumerALL");

        MessageProducer producer = session.createProducer(dest);
        for (int i = 0; i < 3; i++) {
            String receiver = (i % 3 == 0 ? "A" : "B");
            TextMessage message = session.createTextMessage("Message" + i + ", receiver:" + receiver);
            message.setStringProperty("receiver", receiver);
            producer.send(message);
        }
    }

    private static void createComsumer(Destination dest, Session session, String messageSelector, final String consumerName) throws JMSException {
        MessageConsumer comsumerA = session.createConsumer(dest, messageSelector);
        comsumerA.setMessageListener(new MessageListener() {
            public void onMessage(Message m) {
                try {
                    System.out.println(consumerName + " get " + ((TextMessage) m).getText());
                } catch (JMSException e1) {
                }
            }
        });
    }


    private static String arg(String[] args, int i, String string) {
// TODO Auto-generated method stub
        return string;
    }


    private static String env(String string, String string2) {
// TODO Auto-generated method stub
        return string2;
    }
}

