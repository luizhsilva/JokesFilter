package tagger;

import java.util.ArrayList;

import tags.*;

public class Filter {
	
	private Tag inappTag;
	private Tag tooLongTag;
	private Tag lawyerTag;
	private Tag politicalTag;
	
	public Filter() {
		this.inappTag = new InappropriateTag();
		this.tooLongTag = new TooLongTag();
		this.lawyerTag = new LawyerTag();
		this.politicalTag = new PoliticalTag();
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
		
	}
	
	/*
	 * lawyer joke? sport? politic? blonde?
	 */
	public void addType(String s) {
		String str = s.toLowerCase();
		if( str.contains( " lawyer")) this.lawyerTag.addJoke(s);
		
		ArrayList<String> poliWords = initPoliWords();
		for(String string : poliWords){
			if( str.contains(string)) this.politicalTag.addJoke(s);
			break;
		}
	}
	
	private ArrayList<String> initPoliWords() {
		ArrayList<String> poliWords = new ArrayList<String>();
		poliWords.add(" social democrat");
		poliWords.add(" republican");
		poliWords.add(" Bush");
		poliWords.add(" Monica Lewinsky");
		poliWords.add(" Bill Clinton");
		return poliWords;
	}

	/*
	 * too much?
	 */
	public void checkAppropriateness(String s) {
		String str = s.toLowerCase();
		ArrayList<String> inappWords = initInappWords();
		for(String string : inappWords){
			if( str.contains(string)) this.inappTag.addJoke(s);
			break;
		}
	}

	private ArrayList<String> initInappWords() {
		ArrayList<String> inappWords = new ArrayList<String>();
		inappWords.add(" sex ");
		inappWords.add(" erection ");
		inappWords.add(" pussy ");
		inappWords.add(" genital");
		inappWords.add(" gay");
		inappWords.add(" lesbian");
		inappWords.add(" fuck "); inappWords.add(" fucking ");
		inappWords.add(" nigger ");
		inappWords.add(" penis ");
		inappWords.add(" cunt");
		inappWords.add(" viagra");
		inappWords.add(" suck");
		inappWords.add(" asshole");
		inappWords.add(" make love ");
		inappWords.add(" masturbating");
		inappWords.add(" blonde");
		inappWords.add(" yo mama "); inappWords.add(" yo's mama ");
		return inappWords;
	}
}
