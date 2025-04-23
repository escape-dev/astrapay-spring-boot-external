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
        notes.add(note);

        return note;
    }

    public Note getById(int id) {
        return notes.stream()
                .filter(note -> note.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void delete(int id) {
        notes.removeIf(note -> note.getId() == id);
    }

}
