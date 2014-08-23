package tagger;


import reader.DBReader;

public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) {
		DBReader reader = new DBReader();
		//reader.retrieveJokes("resources/contents_1.csv");
	
		// reader.retrieveJokes("resources/contents/contents_11000-11999.csv");
		// for json test
		// Filter f = new Filter();
		// f.checkAppropriateness(" sex");
		// f.checkAppropriateness("yo mamma is like traintracks....");
		// f.checkAppropriateness(" fuck ");
	}

}
