package com.trackers.dictionary.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.trackers.dictionary.business.entities.Flashcard;

public class FlashcardList implements Iterable<Flashcard>, Serializable {
	private static final long serialVersionUID = 202205001L;
	private List<Flashcard> flashcardList = new LinkedList<Flashcard>();
	private static FlashcardList instance;
	
	private FlashcardList() {
		// singleton constructor
	}
	
	public static FlashcardList getInstance() {
		if (instance == null) {
			instance = new FlashcardList();
		}
		return instance;
	}
	
	public Flashcard search(String term) {
		for (Iterator<Flashcard> iterator = flashcardList.iterator(); iterator.hasNext();) {
			Flashcard flashcard = iterator.next();
			if (flashcard.getFront().equalsIgnoreCase(term) || flashcard.getBack().equalsIgnoreCase(term)) {
				return flashcard;
			}
		}
		// TODO FlashcardDoesNotExistError
		return null;
	}
	
	public Flashcard findByFlashcardId(Long flashcardId) {
		for (Iterator<Flashcard> iterator = flashcardList.iterator(); iterator.hasNext();) {
			Flashcard flashcard = iterator.next();
			if (flashcard.getId() == flashcardId) {
				return flashcard;
			}
		}
		return null;
	}
	
	public boolean insertFlashcard(Flashcard flashcard) {
		return flashcardList.add(flashcard);
	}
	
	public boolean removeFlashcard(Long flashcardId) {
		return flashcardList.remove(findByFlashcardId(flashcardId));
	}

	@Override
	public Iterator<Flashcard> iterator() {
		return flashcardList.iterator();
	}

	@Override
	public String toString() {
		return flashcardList.toString();
	}

}
