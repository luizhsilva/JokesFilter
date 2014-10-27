package tagger;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.json.JSONException;

import reader.DBReader;
import tags.InappropriateTag;
import tags.LawyerTag;
import tags.PoliticalTag;
import tags.SportsTag;

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
		 
			BufferedReader br = new BufferedReader(new FileReader("resources/contents/a_jokes_pure.txt"));
		
			 BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources/files/Outputs/allWithoutTooLong.csv"), "UTF-8"));
			 
			 String pn1 = "resources/files/Outputs/allWithTags.csv"; 
			 BufferedWriter all = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(pn1), "UTF-8"));
			 
			 String pn2 = "resources/files/Outputs/allWithoutSpanishJokes.csv";
			 BufferedWriter bw2 = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(pn2), "UTF-8"));
			 
			 String pn3 = "resources/files/Outputs/allWithoutSpanishandTooLongJokes.csv";
			 BufferedWriter bw3 = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(pn3), "UTF-8"));
			 
			 String pn4 = "resources/files/Outputs/LawyerWithoutTooLongJokes.csv";
			 BufferedWriter bw4 = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(pn4), "UTF-8"));
			 
			 String pn5 = "resources/files/Outputs/PoliticalWithoutTooLongJokes.csv";
			 BufferedWriter bw5 = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(pn5), "UTF-8"));
			 
			 String pn6 = "resources/files/Outputs/SportWithoutTooLongJokes.csv";
			 BufferedWriter bw6 = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(pn6), "UTF-8"));
			 
			 String pn7 = "resources/files/Outputs/InappropriateJokes.csv";
			 BufferedWriter bw7 = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(pn7), "UTF-8"));
			 
		 String crLine;
	
		 
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
				f.checkLanguage(jokeString, j);
				//f.addToOriginalJokes(j);
				writeJokeToCSV(j, all);
				
				if (j.getTooLongTag() < 1) {
					dbWithoutTooLongJokes(j,bw1);
				}
				
				if(j.getEnglishTag() == 1) {
					dbWithoutSp(j, bw2);
				}
				
				if((j.getEnglishTag() == 1) && (j.getTooLongTag() < 1)) {
					dbWithoutTLandSp(j, bw3);
				}
				
				if( (j.getLawyerTag() == 1) && (j.getTooLongTag() < 1)) {
					partWithoutTL(j, bw4);
				}
				
				if(j.getPoliTag() == 1 && (j.getTooLongTag() < 1)) {
					partWithoutTL(j, bw5);
				}
				
				if(j.getSportTag() == 1 && (j.getTooLongTag() < 1)) {
					partWithoutTL(j, bw6);
				}
				
				if(j.getInapprTag() == 1 ){
					partWithoutTL(j,bw7);// though it doesn't have something with tooLong to do, 
										// but because the code are the same
				}

				}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 
		 bw1.flush();
		 bw1.close();
		 
		 all.flush();
		 all.close();
		 
		 bw2.flush();
		 bw2.close();
		 
		 bw3.flush();
		 bw3.close();
		 
		 bw4.flush();
		 bw4.close();
		 
		 bw5.flush();
		 bw5.close();
		 
		 bw6.flush();
		 bw6.close();
		 
		 bw7.flush();
		 bw7.close();
		// f.getInappTag().writeFile();
	
		 //f.getLawyerTag().writeFile();
		 
		 //f.getNonEnglishTag().writeFile();
		 
		 //f.getPoliTag().writeFile();
		 
		 //f.getSportTag().writeFile();
		 
		 //f.getTooLongTag().writeFile();
		 
		// ((InappropriateTag) f.getInappTag()).toCSV();
		// ((LawyerTag) f.getLawyerTag()).toCSV();
		// ((PoliticalTag) f.getPoliTag()).toCSV();
		// ((SportsTag) f.getSportTag()).toCSV();
		 			

		// createDBWithoutTooLongJokes(f);
	}
	
	

	/**
	 * 
	 * @param j
	 * @param bw
	 * @throws IOException
	 */
	private static void partWithoutTL(Joke j, BufferedWriter bw) throws IOException {
		StringBuffer oneline = new StringBuffer();
		oneline.append(j.getContent());
		oneline.append("\r");
		bw.write(oneline.toString());
		bw.newLine();
		
	}


	/**
	 * 
	 * @param j
	 * @param bw
	 * @throws IOException
	 */
	private static void dbWithoutTLandSp(Joke j, BufferedWriter bw) throws IOException {
		StringBuffer oneline = new StringBuffer();
		
		oneline.append(j.getContent());
		oneline.append(",");
		oneline.append(j.getInapprTag() == 1? "Inappropriate" : "");
		oneline.append(",");
		oneline.append(j.getLawyerTag() == 1? "Lawyer" : "");
		oneline.append(",");
		oneline.append(j.getPoliTag() == 1 ? "Political" : "");
		oneline.append(",");
		oneline.append(j.getSportTag() == 1 ? "Sport" : "");
	    oneline.append("\r");
		bw.write(oneline.toString());
		bw.newLine();	
	}


	/**
	 * 
	 * @param j
	 * @param bw
	 * @throws IOException
	 */
	private static void dbWithoutSp(Joke j, BufferedWriter bw) throws IOException {
		StringBuffer oneline = new StringBuffer();
		
		oneline.append(j.getContent());
		oneline.append(",");
		oneline.append(j.getTooLongTag() == 1? "TooLong" : "");
		oneline.append(",");
		
		oneline.append(j.getInapprTag() == 1? "Inappropriate" : "");
		oneline.append(",");
		oneline.append(j.getLawyerTag() == 1? "Lawyer" : "");
		oneline.append(",");
		oneline.append(j.getPoliTag() == 1 ? "Political" : "");
		oneline.append(",");
		oneline.append(j.getSportTag() == 1 ? "Sport" : "");
	    oneline.append("\r");
		bw.write(oneline.toString());
		bw.newLine();
	}


	/**
	 * 
	 * @param j
	 * @param bw
	 * @throws IOException
	 */
	private static void writeJokeToCSV(Joke j, BufferedWriter bw) throws IOException {
		StringBuffer oneline = new StringBuffer();
		
		oneline.append(j.getContent());
		oneline.append(",");
		oneline.append(j.getTooLongTag() == 1? "TooLong" : "");
		oneline.append(",");
		oneline.append(j.getEnglishTag() == 0 ? "NonEnglish" : "");
		oneline.append(",");
		oneline.append(j.getInapprTag() == 1? "Inappropriate" : "");
		oneline.append(",");
		oneline.append(j.getLawyerTag() == 1? "Lawyer" : "");
		oneline.append(",");
		oneline.append(j.getPoliTag() == 1 ? "Political" : "");
		oneline.append(",");
		oneline.append(j.getSportTag() == 1 ? "Sport" : "");
	    oneline.append("\r");
		bw.write(oneline.toString());
		bw.newLine();
	}


	/**
	 * 
	 * @param j
	 * @param bw
	 * @throws IOException
	 */
	private static  void dbWithoutTooLongJokes(Joke j, BufferedWriter bw) throws IOException{
	 // ArrayList<Joke> all_jokes = f.getO_jokes().getJokes();
	 // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources/files/Outputs/allWithoutTooLong.csv"), "UTF-8"));
	
		  if(j.getTooLongTag() < 1) {
			  StringBuffer oneline = new StringBuffer();
				oneline.append(j.getContent());
				oneline.append(",");
				oneline.append(j.getEnglishTag() == 0 ? "NonEnglish" : "");
				oneline.append(",");
				oneline.append(j.getInapprTag() == 1? "Inappropriate" : "");
				oneline.append(",");
				oneline.append(j.getLawyerTag() == 1? "Lawyer" : "");
				oneline.append(",");
				oneline.append(j.getPoliTag() == 1 ? "Political" : "");
				oneline.append(",");
				oneline.append(j.getSportTag() == 1 ? "Sport" : "");
			    oneline.append("\r");
				bw.write(oneline.toString());
				bw.newLine();
		  } 

	}
	
}
