package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mdb.Order;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(mappedName="jms/SalutationQueueFactory")
	private QueueConnectionFactory queueConnectionFactory;
	@Resource(mappedName="jms/OrderQueue")
	private Queue queue;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws
    	ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	Connection connection;
        	try {
        		connection = queueConnectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = (MessageProducer) session.createProducer(queue);
                ObjectMessage objectMessage = session.createObjectMessage();
                objectMessage.setObject(new Order(1234,12.5f,40));
                messageProducer.send(objectMessage);
                System.out.println("---> objectMessage sent ");
        	} catch (JMSException ex) {
        		Logger.getLogger(OrderServlet.class.getName()).
        	      log(Level.SEVERE, null, ex);
        	}
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet OrderServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet OrderServlet at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
        } finally {
        	out.close();
        }
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
