package com.nwarz.notes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.nwarz.notes.entity.NoteList;

@Repository
public class NoteListDAO {

	private EntityManager em;

	@PersistenceContext
	private void setEntityManager(EntityManager em) {
		this.em = em;
	}

	
	public List<NoteList> getAllNoteLists() {
		return em.createQuery("FROM NoteList", NoteList.class)
				.getResultList();
	}

	public NoteList getNoteList(long id) {
		return em.find(NoteList.class, id);
	}

	public void renameNoteList(long id, String name) {
		NoteList currList = em.find(NoteList.class, id);
		currList.setName(name);
		em.merge(currList);
	}

	public void deleteNoteList(long id) {
		em.createQuery("DELETE FROM NoteList WHERE id=:notelistId")
			.setParameter("notelistId", id)
			.executeUpdate();
	}

	public void createNoteList(NoteList noteList) {
		em.persist(noteList);
	}

	public boolean exists(long listId) {
		return (null != em.find(NoteList.class, listId));
	}

}
