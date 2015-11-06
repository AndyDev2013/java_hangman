import java.util.*;
import java.io.*;

public class HangMan
{
	public static void main(String[] args ) throws FileNotFoundException

	{
		Scanner inWordsFile = new Scanner(new FileReader("words.dat"));	// Used to read in the words.dat
		Scanner console = new Scanner(System.in);						// Used for input in the console

		String fileWordIn = "";
		String fileRunThrough = "";
		String guessedLetters = "";
		char letterX = '#';
		char myGuess = 'x';
		int i,wordLength,compareCounter,wordReferenceNo;
		int k = 0;
		int fullNumber = 0;
		int tries = 7;
		boolean finder = false;								   // Declaring and initializing variables.

		Random randomNumber = new Random();                    // Calls in the Random Method
		wordReferenceNo = randomNumber.nextInt(19);			   // Uses the Random method to create a number between 0-19 and puts that integer into the variable wordReferenceNo

		if(wordReferenceNo == 0)
		{
			wordReferenceNo += 1;
		} // 0 isn't equal to a word in a file so adding 1 accounts for error handeling

		while(inWordsFile.hasNext()) // Reads in from the inWordsFile as long as there is a next word
		{
			fileRunThrough = inWordsFile.next();	// Assigns the string from words.dat to the string filesRunThrough
			++k;									// Counter adds + 1 after every word is parsed

			if(k == wordReferenceNo)				// If Counter is equal to the random number between 0-19 use that word.
			{
				fileWordIn = fileRunThrough;		// Copies fileRunThrough to a string called fileWordin.
			}
		} // End of the while loop that chooses the word from the data file

		inWordsFile.close(); // Closing the no longer needed data file.
		fileWordIn = fileWordIn.toLowerCase(); // Converts the word to lower case (just incase)

		wordLength = fileWordIn.length();	// fileWordIn.lenght gets the length of the string

//		System.out.print("The word to guess is: " + fileWordIn); // Remove this line if you wish to see the word initially (testing purposes)
		System.out.print("\n==================================================");
		System.out.print("\n=    Welcome to Andrew Hangman Game    =");
		System.out.print("\n=  You have 7 tries to find the word. Goodluck.  =");
		System.out.print("\n=  The lenght of the word you are guessing is: " + wordLength + " =");
		System.out.print("\n==================================================");
		System.out.println();									// Basic Headers

		char[]myWord = fileWordIn.toCharArray();				// Creating a CHARACTER array called my word and putting the string inside it
		char[]wordReveal = new char[myWord.length]; 			// Copies the myWord Character array to a newly created character array called wordReveal

		for(i = 0; i < wordLength; ++i)
		{
			wordReveal[i] = letterX;
		} // This for loops assigns the contents of the wordReveal character array to 'x'

		while(tries != 0)
		{
			System.out.print("\n==================================================");
			System.out.print("\nYou have " + tries + " tries left.");			// Outputs how many tries you have (based on fail attempts)
			System.out.print("\nYou have guessed: " + guessedLetters);
			System.out.print("\nType in a single (lowercase) character to guess: ");

			myGuess = console.next().charAt(0);											// Prompts input from the user and takes 1 character from the user to compare.
//			myGuess = Character.toLowerCase(myGuess);

			guessedLetters += myGuess + ", ";

			for(compareCounter = 0; compareCounter < wordLength; ++compareCounter)
			{
				if(myGuess == myWord[compareCounter])
				{
					if(myGuess == wordReveal[compareCounter])
					{
						System.out.print("You entered character before, you lose a try.");
						--tries;
					}// If someone asks the same character twice this takes a try away

					wordReveal[compareCounter] = myGuess;
					finder = true;

					if(myWord[compareCounter] == wordReveal[compareCounter])
					{
						++fullNumber;
					} // Only adds if the letters are the same

					if(fullNumber == wordLength)
					{
						System.out.print("\nCongratulations the word is " + fileWordIn);
						System.out.print("\nThank you for playing.");
						System.out.print("\n==================================================\n");
						System.exit(0);
					} // Win Condition
				}
			} // Loop that if used to compares the position of the letter from the user to each of the characters in the myWord character array

			if(finder != true)
			{
				--tries;
			} // If finder isn't turned to true your Guess did not match any letter in the myWord character array

			String wordRevealing = new String(wordReveal);        //Creates a new string called wordRevealing to put characters from word reveal character array into for display purposes
			System.out.print("\n==================================================");
			System.out.print("\nWord Reveal = " + wordRevealing);

			finder = false; // Resets the finder

		}// While loop

		System.out.print("\nThe word was: " + fileWordIn + " . You lose the game sorry.");
		System.out.print("\nThanks you for playing.");
		System.out.print("\n==================================================\n");
		System.out.println();
	}

} // End
