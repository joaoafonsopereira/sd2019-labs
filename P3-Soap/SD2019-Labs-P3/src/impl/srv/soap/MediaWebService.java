package impl.srv.soap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.jws.WebService;
import javax.ws.rs.WebApplicationException;

import impl.srv.shared.JavaMedia;
import microgram.api.java.Media;
import microgram.api.java.Result;
import microgram.api.soap.MicrogramException;
import microgram.api.soap.SoapMedia;
import utils.Hash;

@WebService(serviceName=SoapMedia.NAME, targetNamespace=SoapMedia.NAMESPACE, endpointInterface=SoapMedia.INTERFACE)
public class MediaWebService implements SoapMedia {

	private static final String ROOT_DIR = "/tmp/microgram/";
	
	final Media impl;
	
	MediaWebService() {
		new File( ROOT_DIR ).mkdirs();
		this.impl = new JavaMedia();
	}
	
	@Override
	public String upload(byte[] bytes) throws MicrogramException {
		Result<String> result = impl.upload(bytes);
		if(result.isOK()) 
			return result.value();
		else 
			throw new MicrogramException("Conflict...");
	}

	@Override
	public byte[] download(String id) throws MicrogramException {			
		Result<byte[]> result = impl.download(id);
		if( result.isOK() )
			return result.value();
		else
			throw new MicrogramException("File not found...");
	}
}