package com.nwarz.notes.service;

import java.util.List;

import javax.transaction.Transactional;

import com.nwarz.notes.entity.NoteList;
import com.nwarz.notes.entity.NoteItem;

public interface NotesService {

	@Transactional
	public void checkNoteListExists(long listId) throws NullPointerException;


	@Transactional
	public NoteList getNoteList(long listId);

	@Transactional
	public List<NoteList> getAllNoteLists();

	@Transactional
	public long createNoteList(NoteList noteList);

	@Transactional
	public void renameNoteList(long listId, String name);

	@Transactional
	public void deleteNoteList(long listId);


	@Transactional
	public NoteItem getNoteItem(long listId, long itemId);

	@Transactional
	public long createNoteItem(long listId, NoteItem noteItem);

	@Transactional
	public long updateNoteItem(NoteItem todoItem);

	@Transactional
	public void deleteNoteItem(long itemId);

}
