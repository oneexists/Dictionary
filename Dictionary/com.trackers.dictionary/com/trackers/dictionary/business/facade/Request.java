package com.trackers.dictionary.business.facade;

public class Request extends DataTransfer {
	private static Request request;
	private static Long requestFlashcardId;
	private static Long requestVocabularyId;

	private Request() {
		// singleton constructor
	}
	
	public static Request getInstance() {
		if (request == null) {
			request = new Request();
		}
		return request;
	}

	public static Long getRequestFlashcardId() {
		return requestFlashcardId;
	}

	public static void setRequestFlashcardId(Long requestFlashcardId) {
		Request.requestFlashcardId = requestFlashcardId;
	}

	public static Long getRequestVocabularyId() {
		return requestVocabularyId;
	}

	public static void setRequestVocabularyId(Long requestVocabularyId) {
		Request.requestVocabularyId = requestVocabularyId;
	}
	
}
