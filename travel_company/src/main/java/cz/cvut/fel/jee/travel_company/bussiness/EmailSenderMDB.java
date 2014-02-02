package cz.cvut.fel.jee.travel_company.bussiness;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import cz.cvut.fel.jee.travel_company.jms.EmailMessageWrapper;

/**
 * Receives messages from JMS Queue
 * @author Ota
 *
 */

@MessageDriven(name = "EmailSenderMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/email"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class EmailSenderMDB implements MessageListener {
	
	private final static Logger LOGGER = Logger.getLogger(EmailSenderMDB.class.getName());

	@Override
	public void onMessage(Message message) {
		if(message instanceof ObjectMessage){
            ObjectMessage msg = (ObjectMessage) message;
            try {
                EmailMessageWrapper wrapper = (EmailMessageWrapper) msg.getObject();
                LOGGER.info("Message for: " + wrapper.getEmailAddress());
                LOGGER.info("Subject: " + wrapper.getSubject());
                LOGGER.info("Message: " + wrapper.getMessage());
            } catch (JMSException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }else{
            LOGGER.log(Level.WARNING, "Message of wrong type: " + message.getClass().getName(), "");
        }
	}

}