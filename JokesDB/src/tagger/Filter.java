package tagger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import java.io.File;
import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;

import tags.*;

public class Filter {
	
	private Tag inappTag;
	private Tag tooLongTag;
	private Tag lawyerTag;
	private Tag poliTag;
	private Tag sportTag;
	private Tag nonEnglishTag;
	
	private Detector detector; 
	
	private OriginalJokes o_jokes;
	private ArrayList<String> inappWords;
	private ArrayList<String> politicalWords;
	private ArrayList<String> sportWords;

	private ArrayList<String> nonEnglishWords;

	public Filter() {
		this.inappTag = new InappropriateTag();
		this.inappWords = initInappWords();
		this.tooLongTag = new TooLongTag();
		this.lawyerTag = new LawyerTag();
		this.poliTag = new PoliticalTag();
		this.politicalWords = initPoliWords();
		this.sportTag = new SportsTag();
		this.sportWords = initSportWords();
		this.nonEnglishTag = new NonEnglishTag();
		this.o_jokes = new OriginalJokes();
		initNonEnglishWords();
		
		
		try {
			DetectorFactory.loadProfile(new File("resources/libraries/language-detector/profiles"));
			this.detector = DetectorFactory.create();
		} catch (LangDetectException e) {
			e.printStackTrace();
		}

	}

	

	/*
	 * english or spanish?
 	 */
	public void checkLanguage(String s, Joke j) {
//		String str = s.toLowerCase();
//		for (String string: nonEnglishWords) {
//			if (str.contains(string)) {
//				this.nonEnglishTag.addJoke(s);
//				this.nonEnglishTag.writeFile();
//				j.setEnglishTag(0);
//			}else j.setEnglishTag(1);
//			
//		}
		
		try {
			this.detector.append(s);
			String language = this.detector.detect();
			if (language.equals("es")) {
				this.nonEnglishTag.addJoke(s);
				this.nonEnglishTag.writeFile();
				j.setEnglishTag(0);
			} else j.setEnglishTag(1);
		} catch (LangDetectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		if(strs.length >= 120) {
			this.tooLongTag.addJoke(s);
			this.tooLongTag.writeFile();
			 j.setTooLongTag(1);
		}else j.setTooLongTag(0);
		
		((TooLongTag) this.tooLongTag).toCSV();
	}
	
	/*
	 * lawyer joke? sport? politic? 
	 */
	public void addType(String s, Joke j) throws JSONException, IOException {
		String str = s.toLowerCase();
		if( str.contains( " lawyer")){ 
			this.lawyerTag.addJoke(s);
			((LawyerTag) this.lawyerTag).writeFile();
			j.setLawyerTag(1);
			}else j.setLawyerTag(0);
		
		for(String string : politicalWords){
			if( str.contains(string)) {
				this.poliTag.addJoke(s);
				j.setPoliTag(1);
				((PoliticalTag) this.poliTag).writeFile();
			}else j.setPoliTag(0);		
		}
		
		for(String str1 : sportWords) {
			if(str.contains(str1)){
				this.sportTag.addJoke(s);
				this.sportTag.writeFile();
				j.setSportTag(1);
			}else j.setSportTag(0);
		}
		
		((LawyerTag) this.lawyerTag).toCSV();
		((PoliticalTag) this.poliTag).toCSV();
		((SportsTag) this.sportTag).toCSV();
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
		for(String string : inappWords){
			if( str.contains(string)) {
				this.inappTag.addJoke(s);
				((InappropriateTag) inappTag).writeFile();
				j.setInapprTag(1);
			}else j.setInapprTag(0);
		}
		
		((InappropriateTag) inappTag).toCSV();
	}

	private ArrayList<String> initInappWords() {
		ArrayList<String> inappWords = new ArrayList<String>();
		inappWords.add(" sex");
		inappWords.add(" erection "); inappWords.add(" boner "); inappWords.add(" hard on ");
		inappWords.add(" pussy "); inappWords.add(" pussies "); inappWords.add(" pussy");
		inappWords.add(" genital"); 
		inappWords.add(" gay"); 
		inappWords.add(" lesbian"); inappWords.add(" lesbo "); inappWords.add(" lezzie ");
		inappWords.add(" fuck"); inappWords.add(" fucking "); inappWords.add("fuck ");
		inappWords.add("fucker "); inappWords.add(" fucker");
		inappWords.add(" nigger ");inappWords.add(" niggers "); inappWords.add(" jigaboo ");
		inappWords.add(" negro "); inappWords.add(" nigaboo "); inappWords.add(" nigga ");
		inappWords.add(" niglet "); inappWords.add(" nigger");
		inappWords.add(" jungle bunny "); inappWords.add(" junglebunny ");
		inappWords.add(" porch monkey "); inappWords.add(" porchmonkey ");
		inappWords.add(" blow job"); inappWords.add(" blowjob ");
		inappWords.add(" cunt"); inappWords.add(" cunt "); inappWords.add(" cunts ");
		inappWords.add(" viagra"); 
		inappWords.add(" suck");
		inappWords.add(" asshole");
		inappWords.add(" make love ");
		inappWords.add(" masturbating"); inappWords.add(" jerk off ");
		inappWords.add(" blonde");
		inappWords.add("yo mama"); inappWords.add("yo's mama"); 
		inappWords.add("your momma"); inappWords.add("ya mamma");
		inappWords.add("yo mamma"); inappWords.add("yo' mama");
		inappWords.add("yo momma"); inappWords.add("ur mamma");
		inappWords.add(" anus ");
		inappWords.add(" arse "); inappWords.add(" arsehole ");
		inappWords.add(" ass");
		inappWords.add(" beaner "); inappWords.add(" spic "); inappWords.add(" spick ");
		inappWords.add(" wetback ");
		inappWords.add(" bitch");
		inappWords.add(" camel toe "); inappWords.add(" queef ");
		inappWords.add(" chinc "); inappWords.add(" chink "); inappWords.add(" gook ");
		inappWords.add(" gringo ");
		inappWords.add(" dago "); inappWords.add(" deggo "); inappWords.add(" guido ");
		inappWords.add(" dick"); inappWords.add(" dildo ");
		inappWords.add(" dipshit ");
		inappWords.add(" dumbass "); inappWords.add(" dumass "); inappWords.add(" dumb ass");
		inappWords.add(" fag ");
		inappWords.add(" fallatio "); inappWords.add(" wop ");
		inappWords.add(" heeb ");
		inappWords.add(" honky "); inappWords.add(" honkey ");
		inappWords.add(" humping "); inappWords.add(" handjob ");
		inappWords.add(" jackass "); inappWords.add(" jackasses ");
		inappWords.add(" kike "); inappWords.add(" kyke ");
		inappWords.add(" kooch "); inappWords.add(" kootch ");
		inappWords.add(" kraut "); inappWords.add(" krauts ");
		inappWords.add(" kunt ");
		inappWords.add(" lardass "); inappWords.add(" lardasses ");
		inappWords.add(" mick ");
		inappWords.add(" mothafucka ");
		inappWords.add(" muff "); inappWords.add(" muffdiver ");
		inappWords.add(" nut sack "); inappWords.add(" nutsack ");
		inappWords.add(" paki "); 
		inappWords.add(" pecker "); inappWords.add(" peckerhead ");
		inappWords.add(" penis "); inappWords.add(" penis");
		inappWords.add(" queer "); inappWords.add(" queer");
		inappWords.add(" ruski "); inappWords.add(" ruskis ");
		inappWords.add(" slut "); inappWords.add(" slut");
		inappWords.add(" splooge "); inappWords.add(" ejaculate");
		inappWords.add(" spook ");
		inappWords.add(" vagina "); inappWords.add(" vag ");
		inappWords.add(" wank ");
		inappWords.add(" whore "); inappWords.add(" whore");
		return inappWords;
	}
	
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
	
	/**
	 * just for test...it isn't used later
	 * @param contents
	 * @throws JSONException
	 * @throws IOException
	 */
	public void toCSV(ArrayList<String> contents) throws JSONException, IOException{
		
		JSONArray jarr = new JSONArray(contents);
		
		File csv = new File("resources/files/Outputs/InappropriateTag.csv");
		String con = CDL.toString(jarr);
		FileUtils.writeStringToFile(csv, con);

	}
	
	
}
