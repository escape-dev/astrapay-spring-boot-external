package com.astrapay.controller;

import com.astrapay.dto.BaseResponsesDto;
import com.astrapay.dto.NoteRequestDto;
import com.astrapay.dto.ValidationErrorDto;
import com.astrapay.entity.Note;
import io.swagger.annotations.Api;

import com.astrapay.service.NoteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notes")
@Api(value = "NotesController")
public class NotesController {

    private final NoteService noteService;

    @Autowired
    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    @ApiOperation(value = "Get All Notes")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = Note.class )
            }
    )
    public ResponseEntity<BaseResponsesDto<List<Note>>> getAllNotes() {
        return ResponseEntity.ok().body(noteService.getAllNotes());
    }

    @PostMapping
    @ApiOperation(value = "Create Note")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = Note.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ValidationErrorDto.class)
            }
    )
    public ResponseEntity<BaseResponsesDto<Note>> createNote(@Valid @RequestBody NoteRequestDto noteRequestDto) {
        Note note = new Note();
        note.setTitle(noteRequestDto.getTitle());
        note.setDescription(noteRequestDto.getDescription());

        return ResponseEntity.ok().body(noteService.createNote(note));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Note")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = Note.class),
                    @ApiResponse(code = 404, message = "Not Found")
            }
    )
    public ResponseEntity<BaseResponsesDto<Note>> deleteNote(@PathVariable int id) {
        return ResponseEntity.ok().body(noteService.deleteNote(id));
    }
}
