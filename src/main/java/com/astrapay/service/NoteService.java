package com.astrapay.service;

import com.astrapay.dto.BaseResponsesDto;
import com.astrapay.entity.Note;
import com.astrapay.repository.NoteRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public BaseResponsesDto<List<Note>> getAllNotes() {
        BaseResponsesDto<List<Note>> response = new BaseResponsesDto<>();
        response.setResponse(noteRepository.getAll());

        return response;
    }

    public BaseResponsesDto<Note> createNote(Note note) {
        BaseResponsesDto<Note> response = new BaseResponsesDto<>();
        response.setResponse(noteRepository.create(note));

        return response;
    }

    public BaseResponsesDto<Note> deleteNote(int id) {
        Note note = noteRepository.getById(id);

        if (note == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }

        noteRepository.delete(id);

        BaseResponsesDto<Note> response = new BaseResponsesDto<>();
        response.setResponse(note);

        return response;
    }

}
