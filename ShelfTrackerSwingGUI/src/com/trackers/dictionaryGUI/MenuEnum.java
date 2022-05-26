package com.trackers.dictionaryGUI;

public enum MenuEnum {
	ADD_FLASHCARD("Add Flashcard"), ADD_VOCABULARY("Add Vocabulary"), 
	SEARCH_FLASHCARD("Search Flashcards"), SEARCH_VOCABULARY("Search Vocabulary"), 
	REMOVE_FLASHCARD("Remove Flashcard"), REMOVE_VOCABULARY("Remove Vocabulary"), 
	RANDOM_FLASHCARD("Random Flashcard"), RANDOM_VOCABULARY("Random Vocabulary Term"), 
	SAVE("Save"), EXIT("Exit");
	
	private String name;
	
	MenuEnum(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
