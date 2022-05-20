package com.trackers.dictionary.business.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

import static com.trackers.dictionary.business.facade.Result.ResultCode.*;

import com.trackers.dictionary.business.collections.FlashcardList;
import com.trackers.dictionary.business.collections.VocabularyList;
import com.trackers.dictionary.business.entities.Flashcard;
import com.trackers.dictionary.business.entities.Vocabulary;
import com.trackers.dictionary.business.iterators.FlashcardIterator;
import com.trackers.dictionary.business.iterators.VocabularyIterator;

public class Dictionary implements Serializable {
	/**
	 * YYYYMMVVV
	 * Year and month of last update and version
	 */
	private static final long serialVersionUID = 202205001L;
	private FlashcardList flashcardList = FlashcardList.getInstance();
	private VocabularyList vocabularyList = VocabularyList.getInstance();
	private static Dictionary dictionary;
	private static Long idCounter = 0L;
	
	private Dictionary() {
		// singleton constructor
	}
	
	public static Dictionary getInstance() {
		if (dictionary == null) {
			dictionary = new Dictionary();
		}
		return dictionary;
	}
	
	private static Long getId() {
		idCounter++;
		return idCounter;
	}
	
	public Result addFlashcard(Request request) {
		Result result = new Result();
		Flashcard flashcard = new Flashcard(getId(), request.getFlashcardFront(), request.getFlashcardBack(), request.getFlashcardHint());
		if (flashcardList.insertFlashcard(flashcard)) {
			result.setResultCode(OPERATION_COMPLETED);
			result.setFlashcardFields(flashcard);
		} else {
			result.setResultCode(OPERATION_FAILED);			
		}
		return result;
	}
	
	public Result addVocabulary(Request request) {
		Result result = new Result();
		Vocabulary vocabulary = new Vocabulary(getId(), request.getVocabularyWord(), 
				request.getVocabularyAbbreviation(), request.getVocabularyDefinition());
		if (vocabularyList.insertVocabulary(vocabulary)) {
			result.setResultCode(OPERATION_COMPLETED);
			result.setVocabularyFields(vocabulary);
		} else {			
			result.setResultCode(OPERATION_FAILED);
		}
		return result;
	}
	
	public Result searchFlashcard(Request request) {
		Result result = new Result();
		Flashcard flashcard = flashcardList.search(request.getFlashcardTerm());
		// verify flashcard is found
		if (flashcard == null) {
			result.setResultCode(FLASHCARD_NOT_FOUND);
		} else {
			result.setResultCode(OPERATION_COMPLETED);
			result.setFlashcardFields(flashcard);
		}
		return result;
	}
	
	public Result searchVocabulary(Request request) {
		Result result = new Result();
		Vocabulary vocabulary = vocabularyList.search(request.getVocabularyTerm());
		// verify vocabulary is found
		if (vocabulary == null) {
			result.setResultCode(VOCABULARY_NOT_FOUND);
		} else {
			result.setResultCode(OPERATION_COMPLETED);
			result.setVocabularyFields(vocabulary);
		}
		return result;
	}
	
	public Result removeFlashcard() {
		Result result = new Result();
		Flashcard flashcard = flashcardList.findByFlashcardId(Request.getRequestFlashcardId());
		// verify flashcard is found
		if (flashcard == null) {
			result.setResultCode(FLASHCARD_NOT_FOUND);
			return result;
		}
		//remove flashcard
		result.setFlashcardFields(flashcard);
		if (! flashcardList.removeFlashcard(Request.getRequestFlashcardId())) {
			result.setResultCode(OPERATION_FAILED);
			return result;
		}
		// flashcard removed
		result.setResultCode(OPERATION_COMPLETED);
		return result;
	}
	
	public Result removeVocabulary() {
		Result result = new Result();
		Vocabulary vocabulary = vocabularyList.findByVocabularyId(Request.getRequestVocabularyId());
		// verify vocabulary is found
		if (vocabulary == null) {
			result.setResultCode(VOCABULARY_NOT_FOUND);
			return result;
		}
		// remove vocabulary
		result.setVocabularyFields(vocabulary);
		if (! vocabularyList.removeVocabulary(Request.getRequestVocabularyId())) {
			result.setResultCode(OPERATION_FAILED);
			return result;
		}
		// vocabulary removed
		result.setResultCode(OPERATION_COMPLETED);
		return result;
	}
	
	// TODO refactoring load singleton dictionary
	public static Dictionary loadDictionary() {
		try {
			FileInputStream file = new FileInputStream("DictionaryData");
			try (ObjectInputStream input = new ObjectInputStream(file)) {
				dictionary = (Dictionary) input.readObject();
			}
			return dictionary;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}
	
	// TODO refactoring save singleton dictionary
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("DictionaryData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(dictionary);
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
	
	public Iterator<Result> getFlashcards() {
		return new FlashcardIterator(flashcardList.iterator());
	}
	
	public Iterator<Result> getVocabularyTerms() {
		return new VocabularyIterator(vocabularyList.iterator());
	}

	@Override
	public String toString() {
		return "Dictionary \n{\nflashcardList=" + flashcardList + "\n, vocabularyList=" + vocabularyList + "\n}";
	}
	
	
}
