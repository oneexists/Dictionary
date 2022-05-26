package com.trackers.dictionary.business.entities;

import java.io.Serializable;

public class Flashcard implements Serializable {
	private static final long serialVersionUID = 202205001L;
	private Long id;
	private String front;
	private String back;
	private String hint;

	public Flashcard() {
	}

	public Flashcard(Long id, String front, String back, String hint) {
		this.id = id;
		this.front = front;
		this.back = back;
		this.hint = hint;
	}
	
	public Long getId() {
		return id;
	}

	public String getFront() {
		return front;
	}
	
	public void setFront(String front) {
		this.front = front;
	}
	
	public String getBack() {
		return back;
	}
	
	public void setBack(String back) {
		this.back = back;
	}
	
	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	@Override
	public String toString() {
		if (hint != null) {
			return front + "(" + hint + "): " + back;
		}
		return front + ": " + back;
	}

}
