package mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Message-Driven Bean implementation class for: OrderBean
 */
@MessageDriven(mappedName = "jms/OrderQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class OrderBean implements MessageListener {

    /**
     * Default constructor. 
     */
    public OrderBean() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	ObjectMessage objectMessage = (ObjectMessage) message;
    	try {
			Order order = (Order) objectMessage.getObject();
			System.out.println("Part number: " + order.getPartNum());
			System.out.println("Weight: " + order.getWeight());
			System.out.println("Quantity: " + order.getQuantity());
			System.out.println("Order received. Now fuck off.");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
