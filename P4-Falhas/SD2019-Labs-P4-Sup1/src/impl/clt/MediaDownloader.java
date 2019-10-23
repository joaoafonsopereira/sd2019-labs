package impl.clt;
import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.util.logging.Logger;

import discovery.Discovery;
import microgram.api.java.Media;
import microgram.api.java.Result;

public class MediaDownloader {
	
	private static Logger Log = Logger.getLogger(MediaDownloader.class.getName());

	public static final String SERVICE = "Microgram-MediaStorage";

	//private static final File EARTH = new File("earth.jpg");
	
	private static final String ROOT_DIR = "/tmp/microgram/";
	
	private static final String EARTH_ID = "84486F586FA514F31F07057F39B68C673B7A091F";
	
	public static void main(String[] args) throws Exception {
		URI[] mediaURIs = Discovery.findUrisOf(SERVICE, 1);
		if (mediaURIs.length > 0) {
			Media media = MediaClientFactory.getMediaClient(mediaURIs[0]);
			
			//Result<byte[]> file = media.download(EARTH_ID + "a"); -> 404
			Result<byte[]> file = media.download(EARTH_ID);
			File filename = new File(ROOT_DIR + "test");
			
			if(file.isOK()) {
				Log.info("Download completed: " + filename);
				Files.write(filename.toPath(), file.value());
			}
			else
				Log.info("Download failed, reason: " + file.error());
		}
	}
}
