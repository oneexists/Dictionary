package com.trackers.dictionary.business.tests;

import static com.trackers.dictionary.business.facade.Result.ResultCode.*;

import com.trackers.dictionary.business.facade.Dictionary;
import com.trackers.dictionary.business.facade.Request;
import com.trackers.dictionary.business.facade.Result;

public class AutomatedTester {
	private String[] flashcardFront = { "front1", "front2", "front3" };
	private String[] flashcardBack = { "back1", "back2", "back3" };
	private String[] flashcardHint = { "hint1", "hint2", null };
	private String[] vocabularyWord = { "term1", "term2", "term3" };
	private String[] vocabularyAbbreviation = { "abbrev1", "abbrev2", null };
	private String[][] vocabularyDefinition = { {"def1", "def2" }, { "def3" }, {"def4", "def5", "def6" } };
	
	public void testAddFlashcard() {
		for (int count = 0; count < flashcardFront.length; count++) {
			Request.getInstance().setFlashcardFront(flashcardFront[count]);
			Request.getInstance().setFlashcardBack(flashcardBack[count]);
			Request.getInstance().setFlashcardHint(flashcardHint[count]);
			Result result = Dictionary.getInstance().addFlashcard(Request.getInstance());
			assert result.getResultCode() == OPERATION_COMPLETED;
			assert result.getFlashcardFront().equals(flashcardFront[count]);
			assert result.getFlashcardBack().equals(flashcardBack[count]);
			assert result.getFlashcardHint().equals(flashcardHint[count]);
		}
	}
	
	public void testAddVocabulary() {
		for (int count = 0; count < vocabularyWord.length; count++) {
			Request.getInstance().setVocabularyWord(vocabularyWord[count]);
			Request.getInstance().setVocabularyAbbreviation(vocabularyAbbreviation[count]);
			Request.getInstance().setVocabularyDefinition(vocabularyDefinition[count]);
			Result result = Dictionary.getInstance().addVocabulary(Request.getInstance());
			assert result.getResultCode() == OPERATION_COMPLETED;
			assert result.getVocabularyWord().equals(vocabularyWord[count]);
			assert result.getVocabularyAbbreviation().equals(vocabularyAbbreviation[count]);
			assert result.getVocabularyDefinition().equals(vocabularyDefinition[count]);
		}		
	}
	
	public void testSearchFlashcard() {
		Request.getInstance().setFlashcardTerm("back2");
		assert Dictionary.getInstance().searchFlashcard(Request.getInstance()).getFlashcardFront().equals("front2");
		Request.getInstance().setFlashcardTerm("front3");
		assert Dictionary.getInstance().searchFlashcard(Request.getInstance()).getFlashcardBack().equals("back3");
	}
	
	public void testSearchVocabulary() {
		Request.getInstance().setVocabularyTerm("term1");
		assert Dictionary.getInstance().searchVocabulary(Request.getInstance()).getVocabularyAbbreviation().equals("abbrev1");
		Request.getInstance().setVocabularyAbbreviation("abbrev2");
		assert Dictionary.getInstance().searchVocabulary(Request.getInstance()).getVocabularyWord().equals("term2");
	}
	
	public void testAll() {
		testAddFlashcard();
		testAddVocabulary();
		testSearchFlashcard();
		testSearchVocabulary();
	}
	
	public static void main(String[] args) {
		new AutomatedTester().testAll();
		System.out.println("Tests completed.");
	}
}
