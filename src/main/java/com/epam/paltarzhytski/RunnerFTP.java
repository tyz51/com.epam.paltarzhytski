package com.epam.paltarzhytski;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPListParseException;
 
public class RunnerFTP {
	public static void main(String[] args) {
		
		System.out.println("!!!Start connection!!!");					 
		Connnector myftp= Connnector.getInstance();
		
		try {
			myftp.ftpConnect();
		} catch (FTPDataTransferException | FTPAbortedException| FTPListParseException e) {
			e.printStackTrace();
			System.out.println("Erorr!!!");
		}
		myftp.FileInfo();
        while (true){
        	
        	System.out.println("If you want change folder, press '1';");
            System.out.println("If you want download  file,  press '2';");
            System.out.println("If you want go to yhe parent folder, press '3';");
            System.out.println("If you want exit, press '0';");
            String answer=User.Command("Yours choise: ");
                    
            if (answer.equals("1")){
            	myftp.changeDir(User.Command("Enter folder name"));
                myftp.FileInfo();}
            
            if (answer.equals("2")){
            	myftp.fileDownloader(User.Command("Enter file name"));}
                    
            if (answer.equals("3")){
            	myftp.backDirUp();
                myftp.FileInfo();}
                    
            if (answer.equals("0")){
            	try {
            		myftp.disconect();
            	} 
            	catch (IllegalStateException e) {
					e.printStackTrace();}
            break;}
        }
     }              
}



