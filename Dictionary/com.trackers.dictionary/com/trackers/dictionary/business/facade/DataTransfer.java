package com.trackers.dictionary.business.facade;

import com.trackers.dictionary.business.entities.Flashcard;
import com.trackers.dictionary.business.entities.Vocabulary;

public abstract class DataTransfer {
	private Long flashcardId;
	private String flashcardFront;
	private String flashcardBack;
	private String flashcardHint;
	private String flashcardTerm;
	private Long vocabularyId;
	private String vocabularyWord;
	private String vocabularyAbbreviation;
	private String[] vocabularyDefinition;
	private String vocabularyTerm;

	public DataTransfer() {
		reset();
	}
	
	private void reset() {
		flashcardId = null;
		flashcardFront = null;
		flashcardBack = null;
		flashcardHint = null;
		flashcardTerm = null;
		vocabularyId = null;
		vocabularyWord = null;
		vocabularyAbbreviation = null;
		vocabularyDefinition = null;
		vocabularyTerm = null;
	}
	
	public void setFlashcardFields(@SuppressWarnings("exports") Flashcard flashcard) {
		flashcardHint = (flashcard.getHint() != null) ? flashcard.getHint() : "No hint set";
		flashcardId = flashcard.getId();
		flashcardFront = flashcard.getFront();
		flashcardBack = flashcard.getBack();
	}
	
	public void setVocabularyFields(@SuppressWarnings("exports") Vocabulary vocabulary) {
		vocabularyAbbreviation = (vocabulary.getAbbreviation() != null) ? vocabulary.getAbbreviation() : "";
		vocabularyId = vocabulary.getId();
		vocabularyWord = vocabulary.getWord();
		vocabularyDefinition = vocabulary.getDefinitions();
	}

	public String getFlashcardFront() {
		return flashcardFront;
	}

	public void setFlashcardFront(String flashcardFront) {
		this.flashcardFront = flashcardFront;
	}

	public String getFlashcardBack() {
		return flashcardBack;
	}

	public void setFlashcardBack(String flashcardBack) {
		this.flashcardBack = flashcardBack;
	}

	public String getFlashcardHint() {
		return flashcardHint;
	}

	public void setFlashcardHint(String flashcardHint) {
		this.flashcardHint = flashcardHint;
	}

	public String getFlashcardTerm() {
		return flashcardTerm;
	}

	public void setFlashcardTerm(String flashcardTerm) {
		this.flashcardTerm = flashcardTerm;
	}

	public String getVocabularyWord() {
		return vocabularyWord;
	}

	public void setVocabularyWord(String vocabularyWord) {
		this.vocabularyWord = vocabularyWord;
	}

	public String getVocabularyAbbreviation() {
		return vocabularyAbbreviation;
	}

	public void setVocabularyAbbreviation(String vocabularyAbbreviation) {
		this.vocabularyAbbreviation = vocabularyAbbreviation;
	}

	public String[] getVocabularyDefinition() {
		return vocabularyDefinition;
	}

	public void setVocabularyDefinition(String[] vocabularyDefinitions) {
		this.vocabularyDefinition = vocabularyDefinitions;
	}

	public String getVocabularyTerm() {
		return vocabularyTerm;
	}

	public void setVocabularyTerm(String vocabularyTerm) {
		this.vocabularyTerm = vocabularyTerm;
	}

	public Long getFlashcardId() {
		return flashcardId;
	}

	public Long getVocabularyId() {
		return vocabularyId;
	}
	
	
}
