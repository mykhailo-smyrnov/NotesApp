package com.msmyrnov.notes.servise;

import com.msmyrnov.notes.model.Note;
import com.msmyrnov.notes.repository.NoteRepository;
import com.msmyrnov.notes.service.NoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService = new NoteService();

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidUser() throws Exception {
        noteService.add("", new Note());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidNote() throws Exception {
        noteService.add("user", null);
    }

    @Test()
    public void testAddUserNew() {
        when(noteRepository.isUserExist(anyString())).thenReturn(false);
        noteService.add("user", new Note());
        verify(noteRepository, times(1)).createUser(anyString());
        verify(noteRepository, times(1)).create(anyString(), any(Note.class));
    }

    @Test()
    public void testAddUserExist() {
        when(noteRepository.isUserExist(anyString())).thenReturn(true);
        noteService.add("user", new Note());
        verify(noteRepository, never()).createUser(anyString());
        verify(noteRepository, times(1)).create(anyString(), any(Note.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteInvalidUser() throws Exception {
        noteService.delete("", new Note());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteInvalidNote() throws Exception {
        noteService.delete("user", null);
    }

    @Test()
    public void testDelete() {
        noteService.delete("user", new Note());
        verify(noteRepository, times(1)).delete(anyString(), any(Note.class));
    }

}
