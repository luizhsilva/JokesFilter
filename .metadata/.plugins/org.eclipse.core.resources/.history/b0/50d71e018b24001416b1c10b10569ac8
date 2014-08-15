package Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;

import com.mysql.jdbc.Connection;


public class DBReader {
	
	BufferedReader br = null;
	

	public ArrayList<String> retrieveJokes(String filePath) {
		try {
			 
			String sCurrentLine;
	
			br = new BufferedReader(new FileReader(filePath));
	
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
			
			
			
			
			return null;
	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return null;
	}
}	
	
