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
    //Constructor without parametr
    public Connnector() {
        link = "ftp.mozilla.org";
    }
        
    //Constructor whith parametr
    public Connnector(String link) {
        this.link = link;
    }
	
    //meth for connection
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
            }
    }
	
    public void FileInfo(){
        System.out.println("Files and dir on folder: ");
	try {
            list = client.list();
	} 
        catch (IllegalStateException | IOException | FTPIllegalReplyException| FTPException | FTPDataTransferException | FTPAbortedException| FTPListParseException e) {
            e.printStackTrace();
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
    
    public void changeDir(String dirName){
                    try {
                        client.changeDirectory(dirName);
                    } catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException ex) {
                        Logger.getLogger(Connnector.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }           
		    	
    public void backDirUp(){
	try {
	   client.changeDirectoryUp();
        } 
        catch (IllegalStateException | IOException | FTPIllegalReplyException| FTPException e) {
            e.printStackTrace();
	}
    }	
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
        
    public void disconect() throws FTPException, IllegalStateException, IOException, FTPIllegalReplyException{
        client.disconnect(true);
        System.err.println("Good bay!!!");
    }
}
		


