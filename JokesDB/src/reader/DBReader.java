package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class DBReader {
	
	BufferedReader br = null;
	

	/**
	 * Decodes jokes from jokes file (one joke per line)
	 * @param filePath
	 * @return
	 */
	public ArrayList<String> retrieveJokes(String filePath) {
		try {
			 
			String sCurrentLine;
	
			br = new BufferedReader(new FileReader(filePath));
	
			//reads all lines in file
			while ((sCurrentLine = br.readLine()) != null) {
				//Sets begin index with first appearance of tag '<p>'
				int beginIndex = sCurrentLine.indexOf("<p>", 0);
				//Ignores the jokes that don't begin with paragraph <p>
				//FIXME
				if (beginIndex == -1) continue;
				//Corrects index
				beginIndex += 3;
				int endIndex = 0;
				boolean stopFlag = false;
				int tempIndex = beginIndex;
				
				//Will iterate until find the last </p> in the line
				while(!stopFlag) {
					endIndex = tempIndex;
					tempIndex = sCurrentLine.indexOf("</p>", tempIndex + 1);
					if (tempIndex == -1) stopFlag = true;
				}
				//Cuts the string with the correct indexes
				String jokeString = sCurrentLine.substring(beginIndex, endIndex);
				
				//TODO
				//FIXME
				//Instead of printing, should be creating an array with jokes.
				System.out.println(jokeString);
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
	
