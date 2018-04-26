package com.nwarz.notes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwarz.notes.dao.NoteListDAO;
import com.nwarz.notes.dao.NoteItemDAO;
import com.nwarz.notes.entity.NoteList;
import com.nwarz.notes.entity.NoteItem;

@Service
public class NotesServiceImpl implements NotesService {

	@Autowired
	private NoteListDAO noteListDAO;
	
	@Autowired
	private NoteItemDAO noteItemDAO;	


	@Transactional
	public void checkNoteListExists(long listId) throws NullPointerException {
		if(!noteListDAO.exists(listId)) {
			throw new NullPointerException("no list with id ["+listId+"]");
		}
	}

	@Transactional
	public NoteList getNoteList(long listId) {
		return noteListDAO.getNoteList(listId);
	}

	@Override
	public List<NoteList> getAllNoteLists() {
		return noteListDAO.getAllNoteLists();
	}

	@Transactional
	public long createNoteList(NoteList noteList) {
		noteListDAO.createNoteList(noteList);
		return noteList.getId();
	}

	@Transactional
	public void renameNoteList(long id, String name) {
		noteListDAO.renameNoteList(id, name);
	}

	@Transactional
	public void deleteNoteList(long listId) {
		noteListDAO.deleteNoteList(listId);
	}

	@Transactional
	public NoteItem getNoteItem(long listId, long itemId) {
		return noteItemDAO.getNoteItem(itemId);
	}

	@Transactional
	public long createNoteItem(long listId, NoteItem noteItem) {
		NoteList todoList = noteListDAO.getNoteList(listId);
		todoList.addNoteItem(noteItem);
		noteItemDAO.saveNoteItem(noteItem);
		return noteItem.getId();
	}

	@Transactional
	public long updateNoteItem(NoteItem noteItem) {
		noteItemDAO.updateNoteItem(noteItem);
		return noteItem.getId();
	}

	@Transactional
	public void deleteNoteItem(long itemId) {
		noteItemDAO.deleteNoteItem(itemId);
	}

}
