package cz.cvut.fel.jee.travel_company.jms;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;

public class JmsResourceProducer {

	@Produces
	@EmailQueueConnectionFactory
	@Resource(mappedName = "java:/ConnectionFactory")
    private QueueConnectionFactory emailQueueConnectionFactory;
 
    @Resource(mappedName = "java:/queue/email")
    private Queue emailQueue;
    
    @Produces  
    @EmailQueue
    public Queue toDestination()
    {
       return emailQueue;
    }
    
}
