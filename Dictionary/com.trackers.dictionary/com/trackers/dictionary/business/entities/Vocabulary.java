package com.trackers.dictionary.business.entities;

import java.io.Serializable;
import java.util.Arrays;

public class Vocabulary implements Serializable {
	private static final long serialVersionUID = 202205001L;
	private Long id;
	private String word;
	private String abbreviation;
	private String[] definitions;
	
	public Vocabulary() {
	}

	public Vocabulary(Long id, String word, String abbreviation, String[] definitions) {
		this.id = id;
		this.word = word;
		this.abbreviation = abbreviation;
		this.definitions = definitions;
	}

	public Long getId() {
		return id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String[] getDefinitions() {
		return definitions;
	}

	public void setDefinitions(String[] definitions) {
		this.definitions = definitions;
	}

	@Override
	public String toString() {
		if (! abbreviation.isEmpty()) {
			return word + " [" + abbreviation + "] : " + Arrays.toString(definitions);			
		}
		return word + ": " + Arrays.toString(definitions);
	}

}
