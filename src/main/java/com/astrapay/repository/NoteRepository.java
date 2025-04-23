package com.astrapay.repository;

import com.astrapay.entity.Note;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class NoteRepository {

    private final List<Note> notes = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger();

    public List<Note> getAll() {
        return new ArrayList<>(notes);
    }

    public Note create(Note note) {
        note.setId(idGenerator.incrementAndGet());
        notes.add(note.getId(), note);

        return note;
    }

    public Note getById(int id) {
        return notes.get(id);
    }

    public void delete(int id) {
        notes.remove(id);
    }

}
