package tagger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

import org.json.JSONException;

import tags.*;

public class Filter {
	
	private Tag inappTag;
	private Tag tooLongTag;
	private Tag lawyerTag;
	private Tag poliTag;
	private Tag sportTag;
	private Tag nonEnglishTag;
	private Tag racTag;
	private Tag sexTag;
	private Tag offTag;
	
	private Detector detector; 
	
	private OriginalJokes o_jokes;
	//private ArrayList<String> inappWords;
	private ArrayList<String> politicalWords;
	private ArrayList<String> sportWords;
	private ArrayList<String> offensiveWords;
	private ArrayList<String> sexualWords;
	private ArrayList<String> racistWords;

	private ArrayList<String> nonEnglishWords;

	public Filter() {
		this.inappTag = new InappropriateTag();
	//	this.inappWords = initInappWords();
		this.tooLongTag = new TooLongTag();
		this.lawyerTag = new LawyerTag();
		this.poliTag = new PoliticalTag();
		this.politicalWords = initPoliWords();
		this.sportTag = new SportsTag();
		this.sportWords = initSportWords();
		this.nonEnglishTag = new NonEnglishTag();
		this.o_jokes = new OriginalJokes();
		//initNonEnglishWords();
		this.offensiveWords = initOffenWords();
		this.sexualWords = initSexWords();
		this.racistWords = initRacistWords();
		this.racTag = new RacistTag();
		this.offTag = new OffensiveTag();
		this.sexTag = new SexualTag();
		
		try {
			DetectorFactory.loadProfile(new File("resources/libraries/lang-detector/profiles"));
		} catch (LangDetectException e) {
			e.printStackTrace();
		}

	}

	

	private ArrayList<String> initRacistWords() {
		ArrayList<String> rac_Ws = new ArrayList<String>();
		
		rac_Ws.add(" chinc "); rac_Ws.add(" chink "); rac_Ws.add(" gook ");
		rac_Ws.add(" beaner "); rac_Ws.add(" spic "); rac_Ws.add(" spick ");
		rac_Ws.add(" gringo ");
		rac_Ws.add(" dago "); rac_Ws.add(" deggo "); rac_Ws.add(" guido ");
		rac_Ws.add(" fallatio "); rac_Ws.add(" wop ");
		rac_Ws.add(" kike "); rac_Ws.add(" kyke ");
		rac_Ws.add(" heeb ");
		rac_Ws.add(" paki "); 
		rac_Ws.add(" ruski "); rac_Ws.add(" ruskis ");
		rac_Ws.add(" nigger ");rac_Ws.add(" niggers "); rac_Ws.add(" jigaboo ");
		rac_Ws.add(" negro "); rac_Ws.add(" nigaboo "); rac_Ws.add(" nigga ");
		rac_Ws.add(" niglet "); rac_Ws.add(" nigger");
		rac_Ws.add(" jungle bunny "); rac_Ws.add(" junglebunny ");
		rac_Ws.add(" porch monkey "); rac_Ws.add(" porchmonkey ");
		rac_Ws.add(" honky "); rac_Ws.add(" honkey ");
		rac_Ws.add(" chinese"); rac_Ws.add(" japanese");rac_Ws.add(" korean");
		rac_Ws.add(" asian");
		rac_Ws.add(" mexican");
		rac_Ws.add(" black people "); rac_Ws.add(" black guy"); rac_Ws.add(" black woman ");
		rac_Ws.add(" jew "); rac_Ws.add(" jewish ");
		rac_Ws.add(" mick ");
		return rac_Ws;
	}

	private ArrayList<String> initOffenWords() {
		ArrayList<String> off_Ws = new ArrayList<String>();
		
		off_Ws.add(" gay"); 
		off_Ws.add(" lesbian"); off_Ws.add(" lesbo "); off_Ws.add(" lezzie ");
		off_Ws.add("fucker "); off_Ws.add(" fucker");
		off_Ws.add(" asshole");
		off_Ws.add(" blonde");
		off_Ws.add("yo mama"); off_Ws.add("yo's mama"); 
		off_Ws.add("your momma"); off_Ws.add("ya mamma");
		off_Ws.add("yo mamma"); off_Ws.add("yo' mama");
		off_Ws.add("yo momma"); off_Ws.add("ur mamma"); off_Ws.add("yo'' mama");
		off_Ws.add(" slut "); off_Ws.add(" slut");
		off_Ws.add(" whore "); off_Ws.add(" whore");
		off_Ws.add(" lardass "); off_Ws.add(" lardasses ");
		off_Ws.add(" mothafucka ");
		off_Ws.add(" dumbass "); off_Ws.add(" dumass "); off_Ws.add(" dumb ass");
		off_Ws.add(" fag ");
		off_Ws.add(" bitch");
		off_Ws.add(" jackass "); off_Ws.add(" jackasses ");
		
		return off_Ws;
	}



	/*
	 * english or spanish?
 	 */
	public void checkLanguage(String s, Joke j) {

	
		String language = this.detect(s);
		if (language.equals("es")) {
			this.nonEnglishTag.addJoke(s);
		
			j.setEnglishTag(0);
		} else if (language.equals("non")) {
			j.setEnglishTag(0);
		} else j.setEnglishTag(1);
		
	}
	
	private String detect(String s) {
		try {
			this.detector = DetectorFactory.create();
			this.detector.append(s);
			String language = this.detector.detect();
			return language;
		} catch (LangDetectException e) {
		
			return "non";
		}
		
	}

	
	public void addToOriginalJokes(Joke j) {
		this.o_jokes.addJoke(j);
		this.o_jokes.writeFile();
	}
	/*
	 * too long?
	 */
	public void checkLength(String s, Joke j) throws JSONException, IOException {
		String[] strs = s.split(" ");
		if(strs.length >= 150) {
			this.tooLongTag.addJoke(s);
			
			 j.setTooLongTag(1);
		}else j.setTooLongTag(0);
		
	}
	
	/*
	 * lawyer joke? sport? politic? 
	 */
	public void addType(String s, Joke j) throws JSONException, IOException {
		String str = s.toLowerCase();
		if( str.contains( " lawyer")){ 
			this.lawyerTag.addJoke(s);
			j.setLawyerTag(1);
			//((LawyerTag) this.lawyerTag).toCSV();
			}else j.setLawyerTag(0);
		
		for(String string : politicalWords){
			if( str.contains(string)) {
				this.poliTag.addJoke(s);
				j.setPoliTag(1);
			//	((PoliticalTag) this.poliTag).toCSV();
			}		
		}
		if(j.getPoliTag() == -1) {
			j.setPoliTag(0);
		}
		
		for(String str1 : sportWords) {
			if(str.contains(str1)){
				this.sportTag.addJoke(s);
				//((SportsTag) this.sportTag).toCSV();
				j.setSportTag(1);
			}
		}
		if(j.getSportTag() == -1) {
			j.setSportTag(0);
		}
		
		
		
		
	}
	
	private ArrayList<String> initPoliWords() {
		ArrayList<String> poliWords = new ArrayList<String>();
		poliWords.add(" social democrat");
		poliWords.add(" republican");
		poliWords.add(" bush");poliWords.add(" george bush ");
		poliWords.add(" monica lewinsky");
		poliWords.add(" bill clinton");
		poliWords.add(" hillary clinton");
		poliWords.add(" kennedys");poliWords.add(" kennedy ");
		poliWords.add(" irs ");
		poliWords.add(" john kerry");
		poliWords.add(" al gore");
		poliWords.add(" afghanistan ");
		poliWords.add(" dalai lama ");
		poliWords.add(" referendum ");
		poliWords.add(" eu "); poliWords.add(" european union ");
		poliWords.add(" g20"); poliWords.add(" g20 ");
		poliWords.add(" g8"); poliWords.add(" g8 ");
		poliWords.add(" north korea ");
		poliWords.add(" obama ");
		poliWords.add(" mitt romney ");
		poliWords.add(" serbia "); poliWords.add(" syria ");
		return poliWords;
	}

	/*
	 * too much?
	 */
	public void checkAppropriateness(String s, Joke j) throws JSONException, IOException {
		String str = s.toLowerCase();
		for(String string : offensiveWords){
			if( str.contains(string)) {
				this.inappTag.addJoke(s);
				//this.offTag.addJoke(s);
				j.setInapprTag(1);
				j.setOffensiveTag(1);
			}
		}
		if(j.getOffensiveTag() == -1) {
			j.setOffensiveTag(0);
		}
		
		for(String str2 : sexualWords) {
			if( str.contains(str2)){
				this.inappTag.addJoke(s);
				//this.sexTag.addJoke(s);
				j.setInapprTag(1);
				j.setSexTag(1);
			}
		}
		if(j.getSexTag() == -1){
			j.setSexTag(0);
		}
		
		for(String str3 : racistWords){
			if(str.contains(str3)){
				this.inappTag.addJoke(s);
				j.setInapprTag(1);
				j.setRacistTag(1);
			//	this.racTag.addJoke(s);
			}
		}
		
		if(j.getRacistTag() == -1) {
			j.setRacistTag(0);
		}
		if(j.getInapprTag() == -1) { // not set jet
			j.setInapprTag(0);
		}
		
		
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<String> initSexWords() {
		ArrayList<String> SWs = new ArrayList<String>();
		SWs.add(" sex");
		SWs.add(" erection "); SWs.add(" boner "); SWs.add(" hard on ");
		SWs.add(" pussy "); SWs.add(" pussies "); SWs.add(" pussy");
		SWs.add(" genital"); 	
		SWs.add(" fuck"); SWs.add(" fucking "); SWs.add("fuck ");
		SWs.add("fucker "); SWs.add(" fucker");
		SWs.add(" blow job"); SWs.add(" blowjob ");
		SWs.add(" cunt"); SWs.add(" cunt "); SWs.add(" cunts ");
		SWs.add(" viagra"); 
		SWs.add(" suck");	
		SWs.add(" make love ");
		SWs.add(" masturbating"); SWs.add(" jerk off ");	
		SWs.add(" anus ");
		SWs.add(" arse "); SWs.add(" arsehole ");
		SWs.add(" ass");
		SWs.add(" wetback ");		
		SWs.add(" camel toe "); SWs.add(" queef ");
		SWs.add(" dick"); SWs.add(" dildo ");
		SWs.add(" dipshit ");		
		SWs.add(" humping "); SWs.add(" handjob ");		
		SWs.add(" kooch "); SWs.add(" kootch ");
		SWs.add(" kraut "); SWs.add(" krauts ");
		SWs.add(" kunt ");
		SWs.add(" muff "); SWs.add(" muffdiver ");
		SWs.add(" nut sack "); SWs.add(" nutsack ");
		SWs.add(" pecker "); SWs.add(" peckerhead ");
		SWs.add(" penis "); SWs.add(" penis");
		SWs.add(" queer "); SWs.add(" queer");	
		SWs.add(" splooge "); SWs.add(" ejaculate");
		SWs.add(" spook ");
		SWs.add(" vagina "); SWs.add(" vag ");
		SWs.add(" wank ");
		
		return SWs;
	}
	
	/**
	 * 
	 * @return
	 */
	private ArrayList<String> initSportWords() {
		ArrayList<String> sportWords = new ArrayList<String>();
		sportWords.add(" football");
		sportWords.add(" basketball");
		sportWords.add(" tennis");
		sportWords.add(" swim");
		sportWords.add(" badminton");
		sportWords.add(" golf ");
		sportWords.add(" hockey ");
		sportWords.add(" bowling ");
		sportWords.add(" tennis ");
		sportWords.add(" baseball ");
		sportWords.add(" volleyball ");
		sportWords.add(" sport");
		sportWords.add(" worldcup ");
		sportWords.add(" skateboard");
		sportWords.add(" ski");
		sportWords.add(" handball ");
		sportWords.add(" Cristiano Ronaldo ");
		sportWords.add(" Messi ");
		sportWords.add(" olympic");
		sportWords.add(" fifa ");
		sportWords.add(" f1");
		sportWords.add(" nba ");
		sportWords.add(" martial arts ");
		sportWords.add(" judo ");
		sportWords.add(" gymnastics ");
		sportWords.add(" michael jordan");
		sportWords.add(" michael schumacher");
		sportWords.add(" tiger woods");
		sportWords.add(" david beckham");
		sportWords.add(" roger federer");
		sportWords.add(" kobe bryant");
		return sportWords;
	}




	public OriginalJokes getO_jokes() {
		return o_jokes;
	}
	
	/**
	 * 
	 * @return
	 */
	private ArrayList<String> initNonEnglishWords() {
		nonEnglishWords = new ArrayList<String>();
		//For comparison reasons, spaces must be added
		nonEnglishWords.add(" el ");
		nonEnglishWords.add(" la ");
		nonEnglishWords.add(" que ");
		nonEnglishWords.add(" un ");
		nonEnglishWords.add(" una ");
		nonEnglishWords.add(" con ");
		nonEnglishWords.add(" para ");
		nonEnglishWords.add(" como ");
		//If it is the first word in the sentence:
		nonEnglishWords.add("el ");
		nonEnglishWords.add("la ");
		nonEnglishWords.add("que ");
		nonEnglishWords.add("un ");
		nonEnglishWords.add("una ");
		nonEnglishWords.add("con ");
		nonEnglishWords.add("para ");
		nonEnglishWords.add("como ");
		
		return nonEnglishWords;
	}
	

	public Tag getInappTag() {
		return inappTag;
	}



	public Tag getTooLongTag() {
		return tooLongTag;
	}



	public Tag getLawyerTag() {
		return lawyerTag;
	}



	public Tag getPoliTag() {
		return poliTag;
	}



	public Tag getSportTag() {
		return sportTag;
	}



	public Tag getNonEnglishTag() {
		return nonEnglishTag;
	}
	
	
	
}
