package com.nwarz.notes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.nwarz.notes.entity.NoteItem;

@Repository
public class NoteItemDAO {

	private EntityManager em;

	@PersistenceContext
	private void setEntityManager(EntityManager em) {
		this.em = em;
	}

	
	public List<NoteItem> getNoteItem() {
		return em.createQuery("FROM NoteItem", NoteItem.class)
				.getResultList();
	}

	public NoteItem getNoteItem(long id) {
		return em.find(NoteItem.class, id);
	}

	public void saveNoteItem(NoteItem noteItem) {
		em.persist(noteItem);
	}

	public void deleteNoteItem(long itemId) {
		em.createQuery("DELETE FROM NoteItem WHERE id=:noteitemId")
			.setParameter("noteitemId", itemId)
			.executeUpdate();
	}

	public void updateNoteItem(NoteItem noteItem) {
		em.merge(noteItem);
	}

}
