package ru.gb.spring.testing.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gb.spring.testing.database.entity.Note;
import ru.gb.spring.testing.database.repository.NoteRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class NoteServiceTest {
    @InjectMocks
    private NoteService noteService;

    @Mock
    private NoteRepository noteRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllNotesTest() {
        Note note = new Note();
        note.setTitle("Test title");
        note.setContent("Test Content");

        List<Note> exceptedNotes = Collections.singletonList(note);
        when(noteRepository.findAll()).thenReturn(exceptedNotes);
        List<Note> actualNotes = noteService.getAllNotes();
        assertEquals(exceptedNotes, actualNotes);
    }

}