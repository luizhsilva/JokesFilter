package reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gson.Gson;



public class DBReader {
	
	BufferedReader br = null;
	//ArrayList<String> alljokes;

	/*
	public DBReader() {
		this.alljokes = new ArrayList<String>();
	}*/
	/**
	 * Decodes jokes from jokes file (one joke per line)
	 * @param filePath
	 * @return
	 */
	public ArrayList<String> retrieveJokes(String filePath) {
		try {
			 
			String sCurrentLine;
			//Contains jokes obtained from file.
			ArrayList<String> jokesList = new ArrayList<String>();
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
				
				//Adds to list of jokes
				jokesList.add(jokeString);
			}
			
		//	this.alljokes = jokesList;
			//Returns list with jokes
			return jokesList;
	
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
	/*
	public void writeFile() {
		String pathname = "resources/files/Jokes.json";
		PrintWriter writer;
		try {
			writer = new PrintWriter(pathname, "UTF-8");
			writer.write(this.generateJSON());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String generateJSON() {
		Gson creator; 
		creator = new Gson();
		return creator.toJson(this);
	}*/
	
}	
	
