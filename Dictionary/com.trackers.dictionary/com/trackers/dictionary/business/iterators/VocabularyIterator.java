package com.trackers.dictionary.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.trackers.dictionary.business.entities.Vocabulary;
import com.trackers.dictionary.business.facade.Result;

public class VocabularyIterator implements Iterator<Result> {
	private Iterator<Vocabulary> iterator;
	private Result result = new Result();
	
	public VocabularyIterator(Iterator<Vocabulary> iterator) {
		this.iterator = iterator;
	}
	
	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (! iterator.hasNext()) {
			throw new NoSuchElementException("No such vocabulary word");			
		}
		result.setVocabularyFields(iterator.next());
		return result;
	}

}
