package tagger;

import java.lang.reflect.Array;
import java.util.ArrayList;

import tags.*;

public class Filter {
	
	private Tag inappTag;
	private Tag tooLongTag;
	private Tag lawyerTag;
	private Tag poliTag;
	private Tag sportTag;
	private ArrayList<String> inappWords;
	private ArrayList<String> politicalWords;
	private ArrayList<String> sportWords;

	
	public Filter() {
		this.inappTag = new InappropriateTag();
		this.inappWords = initInappWords();
		this.tooLongTag = new TooLongTag();
		this.lawyerTag = new LawyerTag();
		this.poliTag = new PoliticalTag();
		this.politicalWords = initPoliWords();
		this.sportTag = new SportsTag();
		this.sportWords = initSportWords();
	}

	

	/*
	 * english or spanish?
	 */
	public void checkLanguage(String s) {
		
	}

	/*
	 * too long?
	 */
	public void checkLength(String s) {
		String[] strs = s.split(" ");
		if(strs.length >= 100) {
			this.tooLongTag.addJoke(s);
			this.tooLongTag.writeFile();
		}
	}
	
	/*
	 * lawyer joke? sport? politic? blonde?
	 */
	public void addType(String s) {
		String str = s.toLowerCase();
		if( str.contains( " lawyer")){ 
			this.lawyerTag.addJoke(s);
			((LawyerTag) this.lawyerTag).writeFile();
			}
		
		//ArrayList<String> poliWords = initPoliWords();
		for(String string : politicalWords){
			if( str.contains(string)) this.poliTag.addJoke(s);
			((PoliticalTag) this.poliTag).writeFile();
		}
		
		for(String str1 : sportWords) {
			if(str.contains(str1)){
				this.sportTag.addJoke(s);
				this.sportTag.writeFile();
			}
		}
	}
	
	private ArrayList<String> initPoliWords() {
		ArrayList<String> poliWords = new ArrayList<String>();
		poliWords.add(" social democrat");
		poliWords.add(" republican");
		poliWords.add(" bush");
		poliWords.add(" monica lewinsky");
		poliWords.add(" bill clinton");
		poliWords.add(" hillary clinton");
		poliWords.add(" kennedys");
	//	poliWords.add(" IRS");
		poliWords.add(" john kerry");
		poliWords.add(" al gore");
		return poliWords;
	}

	/*
	 * too much?
	 */
	public void checkAppropriateness(String s) {
		String str = s.toLowerCase();
		for(String string : inappWords){
			if( str.contains(string)) this.inappTag.addJoke(s);
		}
		((InappropriateTag) inappTag).writeFile();
	}

	private ArrayList<String> initInappWords() {
		ArrayList<String> inappWords = new ArrayList<String>();
		inappWords.add(" sex");
		inappWords.add(" erection ");
		inappWords.add(" pussy ");
		inappWords.add(" genital");
		inappWords.add(" gay");
		inappWords.add(" lesbian");
		inappWords.add(" fuck "); inappWords.add(" fucking ");
		inappWords.add(" nigger ");
		inappWords.add(" penis ");
		inappWords.add(" blow jobs");
		inappWords.add(" cunt");
		inappWords.add(" viagra");
		inappWords.add(" suck");
		inappWords.add(" asshole");
		inappWords.add(" make love ");
		inappWords.add(" masturbating");
		inappWords.add(" blonde");
		inappWords.add("yo mama"); inappWords.add("yo's mama"); 
		inappWords.add("your momma"); inappWords.add("ya mamma");
		inappWords.add("yo mamma"); inappWords.add("yo' mama");
		inappWords.add("yo momma"); inappWords.add("ur mamma");
		return inappWords;
	}
	
	private ArrayList<String> initSportWords() {
		ArrayList<String> sportWords = new ArrayList<String>();
		sportWords.add(" football");
		sportWords.add(" basketball");
		sportWords.add(" tennis");
		sportWords.add(" swim");
		sportWords.add(" badminton");
		return null;
	}
}
