package cz.cvut.fel.jee.travel_company.bussiness;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

import cz.cvut.fel.jee.travel_company.jms.EmailMessageWrapper;
import cz.cvut.fel.jee.travel_company.jms.EmailQueue;
import cz.cvut.fel.jee.travel_company.jms.EmailQueueConnectionFactory;

/**
 * Sends email message via JMS Queue
 * @author Ota
 *
 */

@Stateless
public class EmailSenderBean {
	
	@Inject
	@EmailQueueConnectionFactory
	private QueueConnectionFactory connectionFactory;

	 @Inject
	 @EmailQueue
	 private Queue emailQueue;

	 public void sendMessage(String emailAddress, String subject, String message) {
		 EmailMessageWrapper jmsMessage = new EmailMessageWrapper(emailAddress, subject, message);
		 Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	         MessageProducer messageProducer = session.createProducer(this.emailQueue);
	         messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
	         messageProducer.setTimeToLive(10000);
	         connection.start();
	         ObjectMessage objectMessage = session.createObjectMessage();
	         objectMessage.setObject(jmsMessage);
	         messageProducer.send(objectMessage);
		} catch (JMSException e) {
			Logger.getLogger(EmailSenderBean.class.getName()).log(Level.SEVERE, "", e);
		} finally{
			if(connection != null){
				try {
                    connection.close();
                } catch (JMSException e) {
                	Logger.getLogger(EmailSenderBean.class.getName()).log(Level.SEVERE, "", e);
                }
			}
		}
         
	 }
	 
}
