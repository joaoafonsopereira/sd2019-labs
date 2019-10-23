package http;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import discovery.Discovery;

public class Client {
	
	
	public static void main(String[] args) throws IOException {
		
		URI[] instances = Discovery.findUrisOf(Server.NAME, 2);
		
		if( instances.length > 0 ) {
			//System.out.println("mais que 0 instancias");
			for(int i = 0; i < instances.length; i++) {
				String uri = instances[i].toURL() + "earth.jpg";
				System.out.println("Uri: " + uri);

				URLConnection conn = new URL(uri).openConnection();
				conn.getHeaderFields().forEach( (h,v) -> {
					System.out.printf("%s: %s\n", h, v);
				});
				System.out.println();
			}
			
		}
		else
			System.out.println("Opa");
	}
	
}
