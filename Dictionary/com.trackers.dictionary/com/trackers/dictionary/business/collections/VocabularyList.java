package com.trackers.dictionary.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.trackers.dictionary.business.entities.Vocabulary;

public class VocabularyList implements Iterable<Vocabulary>, Serializable {
	private static final long serialVersionUID = 202205001L;
	private List<Vocabulary> vocabularyList = new LinkedList<Vocabulary>();
	private static VocabularyList instance;
	
	private VocabularyList() {
		// singleton constructor
	}
	
	public static VocabularyList getInstance() {
		if (instance == null) {
			instance = new VocabularyList();
		}
		return instance;
	}
	
	public Vocabulary search(String term) {
		for (Iterator<Vocabulary> iterator = vocabularyList.iterator(); iterator.hasNext();) {
			Vocabulary vocabulary = iterator.next();
			if (vocabulary.getWord().equalsIgnoreCase(term)) {
				return vocabulary;
			}
			if (vocabulary.getAbbreviation() != null && vocabulary.getAbbreviation().equalsIgnoreCase(term)) {
				return vocabulary;				
			}
		}
		// TODO VocabularyDoesNotExistError
		return null;
	}
	
	public Vocabulary findByVocabularyId(Long vocabularyId) {
		for (Iterator<Vocabulary> iterator = vocabularyList.iterator(); iterator.hasNext();) {
			Vocabulary vocabulary = iterator.next();
			if (vocabulary.getId() == vocabularyId) {
				return vocabulary;
			}
		}
		return null;
	}
	
	public boolean insertVocabulary(Vocabulary vocabulary) {
		return vocabularyList.add(vocabulary);
	}
	
	public boolean removeVocabulary(Long vocabularyId) {
		return vocabularyList.remove(findByVocabularyId(vocabularyId));
	}
	
	@Override
	public Iterator<Vocabulary> iterator() {
		return vocabularyList.iterator();
	}

	@Override
	public String toString() {
		return vocabularyList.toString();
	}
	
}
