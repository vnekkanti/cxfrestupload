package com.visu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

public class FileService {

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(List<Attachment> attachments, @Context HttpServletRequest request) {
		System.out.println(request.getParameter("name") + " : " + request.getParameter("age") + " : " + request.getParameter("btn"));
		for (Attachment attachment : attachments) {
			DataHandler dataHandler = attachment.getDataHandler();
			try{
				MultivaluedMap<String, String> multivaluedMap = attachment.getHeaders();
                String fileName = getFileName(multivaluedMap);
 
                // write & upload file to server
                InputStream inputStream = dataHandler.getInputStream();
                writeToFileServer(inputStream, fileName);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return Response.ok("upload success").build();
	}
	
	private void writeToFileServer(InputStream inputStream, String fileName) {
		 
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File("C:\\cxfrestuploads\\" + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally{
            //release resource, if any
        }
    }
	
	private String getFileName(MultivaluedMap<String, String> multivaluedMap) {
		 
        String[] contentDisposition = multivaluedMap.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
 
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String exactFileName = name[1].trim().replaceAll("\"", "");
                System.out.println("Exact File Name : " + exactFileName);
                return exactFileName;
            }
        }
        return "unknownFile";
    }
}
