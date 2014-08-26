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
	
		// reader.retrieveJokes("resources/contents/contents_13000-13999.csv");
		// for json test
		 Filter f = new Filter();
		// f.checkAppropriateness(" sex");
		// f.checkAppropriateness("yo mamma is like traintracks....");
		// f.checkAppropriateness(" fuck ");
		 /*
		 f.checkAppropriateness("So have you heard about the new movie 64 dalmations? " + 
"It was going to be 102 but Al Gore wanted a recount!");
		 f.addType("So have you heard about the new movie 64 dalmations? " + 
"It was going to be 102 but Al Gore wanted a recount!");
		 f.checkAppropriateness("Yo' mama so stupid, she heard it was chilly out, and got a spoon!");
		 f.addType("Yo' mama so stupid, she heard it was chilly out, and got a spoon!");
         f.addType("What do most women miss most about being single?Having sex!");
         //FIXME if the read in string contains ""?
         f.checkAppropriateness("What do most women miss most about being single?Having sex!");
         f.checkAppropriateness("Two blondes walk in to a bar one blonde ducks because she saw a pole. " +
         		"What does the next blonde do? She walked on to the pole.");
         f.checkLength("A travelling ventriloquist on the road in between jobs decided to practice his craft before his next show. He stopped at a farmhouse and approached the farmer who lived there. " +
         		"Hello there, Mr. Farmer, I was just passing by and I was wondering if I might speak to your dog." +
"The farmer replied, " + "Well, you know, dogs don't talk." +
"The ventriloquist said, "+ "You'd be surprised what a dog might tell you. Can I speak with him?" +
"The farmer, eyeing the ventriloquist suspiciously, called his dog." +
"Hi there, Mr. dog," + " said the ventriloquist. " + "How does the farmer treat you?" +" To which the dog replied, " +"Oh, he's great! He throws a stick for me, scratches my belly, and I just love him!!" +
"Needless to say, the farmer was dumfounded. Wanting to see if he could fool the farmer again, the ventriloquist asked if he could speak with the farmer's horse." +
"Well, you know, horses don't talk." +
"Again the ventriloquist said, " + "You'd be surprised what a horse might tell you." + " So the farmer brought out his horse. " + "Say, Mr. Horse, how does the farmer treat you?" + " asked the ventriloquist. The horse then replied, " + "Oh, I think he's great. He feeds me oats, he puts a blanket over me at night, and I just love him!" + 
" Again the farmer was amazed. Wanting to try his luck a third time, the ventriloquist said, " + "Mr. Farmer, would you like to hear what the sheep has to say about you?" +
"Well," + " declared the farmer, " + "Sheep lie, ya' know.");*/
	}
}
