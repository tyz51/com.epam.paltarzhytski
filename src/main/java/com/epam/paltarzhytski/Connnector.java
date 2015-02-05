package com.epam.paltarzhytski;
import java.io.IOException;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connnector {
		
    private String link;
    FTPFile[] list;
    FTPClient client;
    
	private static Connnector instance; //// for Singleton :)

		   
    //Constructor without parameter
    private Connnector() {
        link = "ftp.mozilla.org";}      
    
    //Constructor with parameter
    private Connnector(String link) {
        this.link = link;}
	
    //Singleton :)
	public static Connnector getInstance(){
		if (instance==null) instance=new Connnector();
		return instance;
	}
	
    //method  for connection
    public void ftpConnect() throws FTPDataTransferException, FTPAbortedException, FTPListParseException{		
	client = new FTPClient();		
	try {
            client.connect(link);
            client.login("anonymous", "ftp4j"); 
            System.out.println("The connection is established!!!");
            System.out.println("Information about connection - "+ client);
            }
            catch (IllegalStateException | IOException | FTPIllegalReplyException| FTPException e) {
            	e.printStackTrace();
            	System.out.println("Can't create connection!!!!");
            }
    }
	
    //method  for get information
    public void FileInfo(){
        System.out.println("Files and dir on folder: ");
	try {
            list = client.list();
	} 
        catch (IllegalStateException | IOException | FTPIllegalReplyException| FTPException | FTPDataTransferException | FTPAbortedException| FTPListParseException e) {
            e.printStackTrace();
            System.out.println("Eror!!!!");
        } 
		for (FTPFile elem:list){
            if (elem.getType()==0){
            	System.out.println("--- FILE: "+elem.getName());
	}
            else{
            	System.out.println("--- DIR: "+elem.getName());
            }
		}			
    }	
    
    //method  for change folder
    public void changeDir(String dirName){
                    try {
                        client.changeDirectory(dirName);
                    } catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException ex) {
                        Logger.getLogger(Connnector.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }           
	
  //method  for return in parent folder
    public void backDirUp(){
    	try {
    		client.changeDirectoryUp();
        } 
        catch (IllegalStateException | IOException | FTPIllegalReplyException| FTPException e) {
            e.printStackTrace();
        }
    }
    
    //method  for file download
    public void fileDownloader(String filename){
    	try {
            File myPath = new File("Download");
            myPath.mkdir();
            client.download(filename, new java.io.File(System.getProperty("user.dir")+"\\"+myPath+"\\"+filename));		
            }
        catch (IllegalStateException | IOException | FTPIllegalReplyException| FTPException | FTPDataTransferException | FTPAbortedException e) {
            e.printStackTrace();
        }
    }
        
    //method  for disconnect and print "Good bay"
    public void disconect(){
        try {
			client.disconnect(true);
        } 
        catch (IllegalStateException | IOException | FTPIllegalReplyException| FTPException e) {
			e.printStackTrace();
			System.out.println("Eror!!!!");
		}
        System.err.println("Good bay!!!");
    }
}
		


