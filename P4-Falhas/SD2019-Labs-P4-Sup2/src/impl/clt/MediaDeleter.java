package impl.clt;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.util.logging.Logger;

import discovery.Discovery;
import microgram.api.java.Media;
import microgram.api.java.Result;

public class MediaDeleter {

	private static Logger Log = Logger.getLogger(MediaDeleter.class.getName());

	public static final String SERVICE = "Microgram-MediaStorage";
	
	private static final String ID = "84486F586FA514F31F07057F39B68C673B7A091F";
	
	public static void main(String[] args) throws Exception {
		
		URI[] mediaURIs = Discovery.findUrisOf(SERVICE, 1);
		
		if (mediaURIs.length > 0) {
			Media media = MediaClientFactory.getMediaClient(mediaURIs[0]);

			/*
			Result<Void> res = media.delete(ID);
			
			if (res.isOK())
				Log.info("Deletion completed.");
			else
				Log.info("Deletion failed, reason: " + res.error());
			*/
		}
		
	}
}
