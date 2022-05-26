package com.trackers.dictionaryUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.trackers.dictionary.business.facade.Result.ResultCode.*;

import com.trackers.dictionary.business.facade.Dictionary;
import com.trackers.dictionary.business.facade.Request;
import com.trackers.dictionary.business.facade.Result;

public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static final int EXIT = 0;
	private static final int ADD_FLASHCARD = 1;
	private static final int ADD_VOCABULARY = 2;
	private static final int SEARCH_FLASHCARD = 3;
	private static final int SEARCH_VOCABULARY = 4;
	private static final int REMOVE_FLASHCARD = 5;
	private static final int REMOVE_VOCABULARY = 6;
	private static final int RANDOM_FLASHCARD = 7;
	private static final int RANDOM_VOCABULARY = 8;
	private static final int SAVE = 9;
	
	private UserInterface() {
		System.out.println("Use saved data?");
			try {
				if (yesOrNo(reader.readLine())) {
					Dictionary.loadDictionary();
				}
			} catch (IOException e) {
				System.out.println("Error initializing interface.");
			}

	}
	
	public static UserInterface getInstance() {
		if (userInterface == null) {
			userInterface = new UserInterface();
		}
		return userInterface;
	}
	
	private boolean yesOrNo(String prompt) {
		return (prompt.startsWith("y") || prompt.startsWith("Y")) ? true : false;
	}
	
	public int getCommand() {
		do {
			try {
				printMenu();
				int selection = Integer.parseInt(reader.readLine());
				if (selection >= EXIT && selection <= SAVE) {
					return selection;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Error in menu selection.");
			} catch (IOException ioe) {
				return 0;
			}
		} while (true);
	}
	
	private void printMenu() {
		System.out.println("Menu:");
		System.out.println(EXIT + " to exit");
		System.out.println(ADD_FLASHCARD + " to add a flashcard");
		System.out.println(ADD_VOCABULARY + " to add a vocabulary term");
		System.out.println(SEARCH_FLASHCARD + " to search flashcards");
		System.out.println(SEARCH_VOCABULARY + " to search vocabulary terms");
		System.out.println(REMOVE_FLASHCARD + " to remove a flashcard");
		System.out.println(REMOVE_VOCABULARY + " to remove a vocabulary term");
		System.out.println(RANDOM_FLASHCARD + " to review a random flashcard");
		System.out.println(RANDOM_VOCABULARY + " to review a random vocabulary term");
		System.out.println(SAVE + " to save to disk");
		System.out.println();
		System.out.println("Select option: ");
	}

	public void process() {
		int command;
		while((command = getCommand()) != EXIT) {
			switch(command) {
			case SAVE:
				save();
				break;
			case ADD_FLASHCARD:
				addFlashcard();
				break;
			case ADD_VOCABULARY:
				addVocabulary();
				break;
			case SEARCH_FLASHCARD:
				searchFlashcard();
				break;
			case SEARCH_VOCABULARY:
				searchVocabulary();
				break;
			case REMOVE_FLASHCARD:
				removeFlashcard();
				break;
			case REMOVE_VOCABULARY:
				removeVocabulary();
				break;
			case RANDOM_FLASHCARD:
				randomFlashcard();
				break;
			case RANDOM_VOCABULARY:
				randomVocabulary();
				break;
			default:
				System.out.println("Invalid selection.");
			}
		}
	}

	private void randomVocabulary() {
		System.out.println("random vocabulary definition: ");
		System.out.println("guess vocabulary term: ");
	}

	private void randomFlashcard() {
		System.out.println("random flashcard front: ");
		System.out.println("guess flashcard back: ");
	}

	private void save() {
		if (Dictionary.save()) {
			System.out.println("The dictionary has been saved in the file DictionaryData.");
		} else {
			System.out.println("Error saving the dictionary.");
		}
	}
	
	private String getText(String prompt) {
		do {
			try {
				System.out.println(prompt);
				return reader.readLine();
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}
	
	private void addFlashcard() {
		Request.getInstance().setFlashcardFront(getText("Front of flashcard: "));
		Request.getInstance().setFlashcardBack(getText("Back of flashcard: "));
		if (yesOrNo(getText("Add hint? "))) {
			Request.getInstance().setFlashcardHint(getText("Hint: "));
		}
		
		Result result = Dictionary.getInstance().addFlashcard(Request.getInstance());
		if (result.getResultCode() != OPERATION_COMPLETED) {
			System.out.println("Could not add flashcard.");
		} else {
			System.out.println(result.getFlashcardFront() + "'s flashcard id is " + result.getFlashcardId());
		}
	}

	private void addVocabulary() {
		Request.getInstance().setVocabularyWord(getText("Vocabulary term: "));
		if (yesOrNo(getText("Add abbreviation? "))) {
			Request.getInstance().setVocabularyAbbreviation(getText("Abbreviation: "));
		}
		List<String> definitions = new ArrayList<>();
		while (yesOrNo(getText("Add definition? "))) {
			definitions.add(getText("Definition: "));
		}
		Request.getInstance().setVocabularyDefinition(definitions.toArray(new String[definitions.size()]));	

		Result result = Dictionary.getInstance().addVocabulary(Request.getInstance());
		if (result.getResultCode() != OPERATION_COMPLETED) {
			System.out.println("Could not add vocabulary term.");
		} else {
			System.out.println(result.getVocabularyWord() + "'s vocabulary id is " + result.getVocabularyId());
		}
	}
	
	private void searchFlashcard() {
		Request.getInstance().setFlashcardTerm(getText("Search Flashcards: "));
		
		Result result = Dictionary.getInstance().searchFlashcard(Request.getInstance());
		if (result.getResultCode() != OPERATION_COMPLETED) {
			System.out.println("Could not find flashcard for " + Request.getInstance().getFlashcardTerm());
		} else {
			System.out.println(result.getFlashcardFront() + " : " + result.getFlashcardBack());
		}
	}
	
	private void searchVocabulary() {
		Request.getInstance().setVocabularyTerm(getText("Search Vocabulary: "));
		
		Result result = Dictionary.getInstance().searchVocabulary(Request.getInstance());
		if (result.getResultCode() != OPERATION_COMPLETED) {
			System.out.println("Could not find term " + Request.getInstance().getVocabularyTerm());
		} else {
			System.out.print(result.getVocabularyWord());
			if (! result.getVocabularyAbbreviation().isEmpty()) {
				System.out.print(" [" + result.getVocabularyAbbreviation() + "]");
			}
			System.out.println(": " + Arrays.toString(result.getVocabularyDefinition()));
		}
	}
	
	private void removeFlashcard() {
		Request.getInstance().setFlashcardTerm(getText("Enter flashcard term to remove: "));
		Result flashcardResult = Dictionary.getInstance().searchFlashcard(Request.getInstance());
		if (flashcardResult.getResultCode() != OPERATION_COMPLETED) {
			System.out.println("Could not find flashcard.");
			return;
		}
		Request.setRequestFlashcardId(flashcardResult.getFlashcardId());
		Result removeFlashcard = Dictionary.getInstance().removeFlashcard();
		if (removeFlashcard.getResultCode() != OPERATION_COMPLETED) {
			System.out.println("Could not remove flashcard.");
			return;
		}
		System.out.println("Flashcard " + flashcardResult.getFlashcardFront() + " removed.");
	}
	
	private void removeVocabulary() {
		Request.getInstance().setVocabularyTerm(getText("Enter vocabulary term to remove: "));
		Result vocabularyResult = Dictionary.getInstance().searchVocabulary(Request.getInstance());
		if (vocabularyResult.getResultCode() != OPERATION_COMPLETED) {
			System.out.println("Could not find vocabulary term.");
			return;
		}
		Request.setRequestVocabularyId(vocabularyResult.getVocabularyId());
		Result removeVocabulary = Dictionary.getInstance().removeVocabulary();
		if (removeVocabulary.getResultCode() != OPERATION_COMPLETED) {
			System.out.println("Could not remove vocabulary term.");
			return;
		}
		System.out.println("Vocabulary term " + vocabularyResult.getVocabularyWord() + " removed.");
		
	}

	public static void main(String[] args) {
		UserInterface.getInstance().process();
	}
}
