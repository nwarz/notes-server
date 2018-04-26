package com.nwarz.notes.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "notelist")
public class NoteList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<NoteItem> noteItems;

	
	public NoteList() {
	}

	public NoteList(String name, List<NoteItem> noteItems) {
		this.name = name;
		this.noteItems = noteItems;
	}

	
	public void addNoteItem(NoteItem noteItem) {
		noteItems.add(noteItem);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NoteItem> getNoteItems() {
		return noteItems;
	}

	public void setNoteItems(List<NoteItem> noteItems) {
		this.noteItems = noteItems;
	}

	public void copy(NoteList noteList) {
		this.name = noteList.getName();
		this.noteItems = noteList.noteItems;
	}

}
