package tagger;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.JSONException;

import reader.DBReader;

public class Main {

	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws JSONException, IOException {
		DBReader reader = new DBReader();
		reader.retrieveJokes("resources/contents/a_jokes_pure.txt");
	
	
		 Filter f = new Filter();
		 try {
			BufferedReader br = new BufferedReader(new FileReader("resources/contents/a_jokes_pure.txt"));
		
		 String crLine;
		//String jokeString;
		 try {
			while( (crLine =  br.readLine()) != null) 
			 	{
				int beginIndex = crLine.indexOf("<p>", 0);
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
					tempIndex = crLine.indexOf("</p>", tempIndex + 1);
					if (tempIndex == -1) stopFlag = true;
				}
				//Cuts the string with the correct indexes
				String jokeString = crLine.substring(beginIndex, endIndex);
				//save all original jokes
				
				Joke j = new Joke();
				j.setContent(jokeString);
				
				
				f.checkLength(jokeString, j);
				f.addType(jokeString,j);
				f.checkAppropriateness(jokeString,j);
				f.addToOriginalJokes(j);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 createDBWithoutTooLongJokes(f);
	}
	
	private static void createDBWithoutTooLongJokes(Filter f) throws IOException{
	  ArrayList<Joke> all_jokes = f.getO_jokes().getJokes();
	  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources/files/Outputs/allWithoutTooLong.csv"), "UTF-8"));
	  for(Joke j : all_jokes) {
		  if(j.getTooLongTag() < 1) {
			  StringBuffer oneline = new StringBuffer();
				oneline.append(j.getContent());
				oneline.append("\r");
				bw.write(oneline.toString());
				bw.newLine();
		  }
	  }
	  bw.flush();
	  bw.close();
	}
	
}
