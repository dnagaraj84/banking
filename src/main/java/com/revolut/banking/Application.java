/**
 * 
 */
package com.revolut.banking;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import com.revolut.banking.controller.AccountController;

/**
 * @author Nags
 *
 */
public class Application 
{
	private static Logger log = Logger.getLogger(Application.class);

	public static void main(String[] args) throws Exception 
	{	
		log.info("Starting demo .....");		
		// Host service on JETTY
		startService();		
	}

	private static void startService() throws Exception 
	{
		Server server = new Server(8080);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/*");
		servletHolder.setInitParameter("jersey.config.server.provider.classnames",
				AccountController.class.getCanonicalName());
		try 
		{
			server.start();
			server.join();
		} 
		finally 
		{
			server.destroy();
		}
	}

}
