package pwr.tin.tip.sw.pd.eai;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.w3c.dom.DocumentFragment;
import org.apache.activemq.ActiveMQConnection;
import org.apache.servicemix.common.util.URIResolver;

public class AppTest 
{
	private ActiveMQConnection connection;
	private Session session;
	private MessageConsumer consumer;
	private MessageProducer producer;
	
	@Test
	public void test1() {
		DocumentFragment epr = URIResolver.createWSAEPR("jbi:endpoint:http://pwr.tin.tip.sw.pd.eai/infoDSLRouter/EAI");
		assertNotNull(epr);
	}
	
	//@Before
	public void initialize() {
		try {
			connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
		    connection.start();
		    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		    Destination destination = session.createQueue("queue/INFO");
		    producer = session.createProducer(destination);
		    Destination responseDestination = session.createQueue("queue/INFO-REPLY");
		    consumer = session.createConsumer(responseDestination);
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void test() {
		try {
			TextMessage message = session.createTextMessage("<alghoritms><countAlghoritm><filePath>C:/Documents and Settings/ROOT/Desktop/lista_do_weryfikacji.txt</filePath><word>osob</word></countAlghoritm></alghoritms>");
			producer.send(message);
			TextMessage response = (TextMessage)consumer.receive(2000);
			System.out.println("received response " + response.getText());
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
