package com.nwarz.notes.controller;

import java.util.List;

import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nwarz.notes.entity.NoteList;
import com.nwarz.notes.entity.NoteItem;
import com.nwarz.notes.service.NotesService;

@RestController
@CrossOrigin
@RequestMapping("/lists")
public class NotesController {

	@Autowired
	private NotesService notesService;

	
	// note list mappings

	@GetMapping("/{listid}")
	@ResponseBody
	public NoteList getNoteList(@PathVariable("listid") long listId) {
		return notesService.getNoteList(listId);
	}

	@GetMapping
	@ResponseBody
	public List<NoteList> getAllNoteLists() {
		return notesService.getAllNoteLists();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public long createNoteList(@RequestBody NoteList noteList) {
		Preconditions.checkNotNull(noteList);
		return notesService.createNoteList(noteList);
	}

	@PutMapping("/{listid}")
	@ResponseStatus(HttpStatus.OK)
	public void renameNoteList(@PathVariable("listid") long listId, @RequestBody String name) {
		notesService.checkNoteListExists(listId);
		notesService.renameNoteList(listId, name);
	}

	@DeleteMapping("/{listid}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteNoteList(@PathVariable("listid") long listId) {
		notesService.deleteNoteList(listId);
	}

	
	// note item mappings

	@GetMapping("/{listid}/items/{itemid}")
	@ResponseBody
	public NoteItem getNoteItem(@PathVariable("listid") long listId, @PathVariable("itemid") long itemId) {
		return notesService.getNoteItem(listId, itemId);
	}

	@PostMapping("/{listid}/items")
	@ResponseStatus(HttpStatus.CREATED)
	public long createNoteItem(@PathVariable("listid") long listId, @RequestBody NoteItem noteItem) {
		Preconditions.checkNotNull(noteItem);
		notesService.checkNoteListExists(listId);
		return notesService.createNoteItem(listId, noteItem);
	}

	@PutMapping("/{listid}/items/{itemid}")
	@ResponseStatus(HttpStatus.OK)
	public long updateNoteItem(@PathVariable("listid") long listId,@PathVariable("itemid") long itemId,
			@RequestBody NoteItem noteItem) {
		Preconditions.checkNotNull(noteItem);
		notesService.checkNoteListExists(listId);
		return notesService.updateNoteItem(noteItem);
	}

	@DeleteMapping("/{listid}/items/{itemid}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteNoteItem(@PathVariable("itemid") long itemId) {
		notesService.deleteNoteItem(itemId);
	}

}
