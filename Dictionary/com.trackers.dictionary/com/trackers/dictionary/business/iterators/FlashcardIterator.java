package com.trackers.dictionary.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.trackers.dictionary.business.entities.Flashcard;
import com.trackers.dictionary.business.facade.Result;

public class FlashcardIterator implements Iterator<Result> {
	private Iterator<Flashcard> iterator;
	private Result result = new Result();
	
	
	public FlashcardIterator(Iterator<Flashcard> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (! iterator.hasNext()) {
			throw new NoSuchElementException("No such flashcard");
		}
		result.setFlashcardFields(iterator.next());
		return result;
	}

}
