package WestendTheatre.model;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Booking {
	/*
	 * create the txt file, save the booking information inside
	 * read the booking information from make booking controller
	 */
	 private static String path = "F:/study tool/AP assignment/Theatre/bookinginfo.txt";  
	  
	    private static File bookinginfo = new File(path);  
	  
	    private static String readStr = ""; 
	    
	    public void creatTxtFile() throws IOException {  
	        if (!bookinginfo.exists()) {  
	        	bookinginfo.createNewFile();  
	        }  
	    }  
	    
	    public String readTxtFile() throws IOException {  
	        String strs = "";  
	        try {  
	            FileReader read = new FileReader(bookinginfo);  
	            StringBuffer sb = new StringBuffer();  
	            char ch[] = new char[1024];  
	            int d = read.read(ch);  
	            while (d != -1) {  
	                String str = new String(ch, 0, d);  
	                sb.append(str);  
	                d = read.read(ch);  
	            }  
	  
	            strs = sb.toString();  
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }   
	        return strs;  
	    }  
	    
	    public void writeTxtFile(String memberID,String seat,String playname,String showdate,String showtime) throws IOException {  
	    	  
	        creatTxtFile();  
	        String str = readTxtFile();  
	          
	        FileWriter fw = new FileWriter(path);  
 
	            fw.write("MemberID: "+memberID+"\n");
	            fw.write("BookingSeat: "+seat+"\n");
	            fw.write("ShowName: "+playname+"\n");
	            fw.write("ShowDate: "+showdate+"\n");
	            fw.write("ShowTime: "+showtime+"\n");

	        fw.close();  
	    }  
}
