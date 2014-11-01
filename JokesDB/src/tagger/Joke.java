package tagger;

import java.util.ArrayList;

import tags.Tag;


/**
 * 
 * @author Luiz Silva
 * @author Xizhe
 * @version 1.1
 *
 */

//Maybe it won't be used.

public class Joke {

	private String content;

	private int inapprTag;
	private int tooLongTag;
	private int poliTag;
	private int englishTag;
	private int sportTag;
	private int lawyerTag;
	private int offensiveTag;
	private int racistTag;
	private int sexTag;
	
	
	public Joke(String content) {
		this.content = content;
			}
	
	public Joke() {
		this.content = null;
		
		this.englishTag = -1;
		this.inapprTag = -1;
		this.offensiveTag = -1;
		this.sexTag = -1;
		this.racistTag = -1;
		this.lawyerTag = -1;
		this.poliTag = -1;
		this.sportTag = -1;
		this.tooLongTag = -1; // init to -1, if true then set to 1, if false then 0
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getInapprTag() {
		return inapprTag;
	}

	public void setInapprTag(int inapprTag) {
		this.inapprTag = inapprTag;
	}
	
	public int getTooLongTag() {
		return tooLongTag;
	}

	public void setTooLongTag(int tooLongTag) {
		this.tooLongTag = tooLongTag;
	}

	public int getPoliTag() {
		return poliTag;
	}

	public void setPoliTag(int poliTag) {
		this.poliTag = poliTag;
	}

	public int getEnglishTag() {
		return englishTag;
	}

	public void setEnglishTag(int englishTag) {
		this.englishTag = englishTag;
	}

	public int getSportTag() {
		return sportTag;
	}

	public void setSportTag(int sportTag) {
		this.sportTag = sportTag;
	}

	public int getLawyerTag() {
		return lawyerTag;
	}

	public void setLawyerTag(int lawyerTag) {
		this.lawyerTag = lawyerTag;
	}

	public int getOffensiveTag() {
		return offensiveTag;
	}

	public void setOffensiveTag(int offensiveTag) {
		this.offensiveTag = offensiveTag;
	}

	public int getRacistTag() {
		return racistTag;
	}

	public void setRacistTag(int racistTag) {
		this.racistTag = racistTag;
	}

	public int getSexTag() {
		return sexTag;
	}

	public void setSexTag(int sexTag) {
		this.sexTag = sexTag;
	}


	
	
}
