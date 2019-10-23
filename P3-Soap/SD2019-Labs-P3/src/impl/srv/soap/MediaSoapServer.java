package impl.srv.soap;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.xml.ws.Endpoint;

import com.sun.net.httpserver.HttpServer;

import utils.IP;

@SuppressWarnings("restriction")
public class MediaSoapServer {
	private static Logger Log = Logger.getLogger(MediaSoapServer.class.getName());

	static {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s\n");
	}
	
	public static final int PORT = 7777;
	public static final String SERVICE = "Microgram-MediaStorage";
	public static String SOAP_BASE_PATH = "/soap"; // /soap ou /soap/media ?
	
	public static void main(String[] args) throws Exception {
		
        // Create an HTTP server, accepting requests at PORT (from all local interfaces)
		HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", PORT), 0);

		// Create the SOAP Endpoint
		Endpoint soapEndpoint = Endpoint.create(new MediaWebService());
<<<<<<< HEAD
		
        // Publish the SOAP webservice, under the "http://<ip>:<port>/soap" 
        soapEndpoint.publish( server.createContext(SOAP_BASE_PATH));

       
        // Start Serving Requests: both SOAP Requests
        server.start();
        
=======

		// Publish the SOAP webservice, under the "http://<ip>:<port>/soap"
		soapEndpoint.publish(server.createContext(SOAP_BASE_PATH));

		// Provide an executor to create threads as needed...
		server.setExecutor(Executors.newCachedThreadPool());

		// Start Serving Requests: both SOAP Requests
		server.start();

>>>>>>> master
		String ip = IP.hostAddress();
		Log.info(String.format("%s Soap Server ready @ %s\n", SERVICE, ip + ":" + PORT));
	}
}
