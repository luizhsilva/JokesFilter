package Tagger;

import java.sql.SQLException;

import Reader.DBReader;

public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		DBReader reader = new DBReader();
		reader.retrieveJokes("resources/contents.csv");
		
	}

}
