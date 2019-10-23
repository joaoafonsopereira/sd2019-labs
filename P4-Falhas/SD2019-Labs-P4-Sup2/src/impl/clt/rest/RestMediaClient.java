package impl.clt.rest;

import java.net.URI;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import microgram.api.java.Media;
import microgram.api.java.Result;
import microgram.api.rest.RestMediaStorage;

public class RestMediaClient extends RestClient implements Media {

	public RestMediaClient(URI mediaStorage) {
		super(mediaStorage, RestMediaStorage.PATH);
	}

	public Result<String> upload(byte[] bytes) {
		return super.reTry(() -> _upload_v1(bytes));
		//return super.reTry(() -> _upload_v2(bytes));
	}

	public Result<byte[]> download(String url) {
		return super.reTry(() -> _download_v2(url));
	}
	
	Result<byte[]> _download_v1(String id) {
		Response r = super.target.path(id).request().accept(MediaType.APPLICATION_OCTET_STREAM)
			 	.get();
		
		if (r.getStatusInfo().equals(Status.OK) && r.hasEntity()) 
			return Result.ok(r.readEntity(byte[].class));
		else
			return Result.error(super.errorCode(r.getStatus()));
	}
	
	Result<byte[]> _download_v2(String id) {
		Response r = super.target.path(id).request().accept(MediaType.APPLICATION_OCTET_STREAM)
			 	.get();
		
		return super.responseContents(r, Status.OK, new GenericType<byte[]>() {});
	}

	Result<String> _upload_v1(byte[] bytes) {
		Response r = super.target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(bytes, MediaType.APPLICATION_OCTET_STREAM));

		if (r.getStatusInfo().equals(Status.OK))
			return Result.ok(r.readEntity(String.class));
		else
			return Result.error(super.errorCode(r.getStatus()));
	}

	Result<String> _upload_v2(byte[] bytes) {
		Response r = super.target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(bytes, MediaType.APPLICATION_OCTET_STREAM));

		return super.responseContents(r, Status.OK, new GenericType<String>() {
		});
	}

	@Override
	public Result<Void> delete(String id) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return null;
=======
		throw new RuntimeException("Not Implemented...");
>>>>>>> master
	}
}