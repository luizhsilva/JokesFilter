package Tagger;


import Reader.DBReader;

public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) {
		DBReader reader = new DBReader();
		//reader.retrieveJokes("resources/contents_1.csv");
	
		reader.retrieveJokes("resources/contents.csv");
	}

}
