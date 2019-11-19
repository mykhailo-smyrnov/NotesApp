package com.msmyrnov.notes.repository;

import com.msmyrnov.notes.model.Note;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NoteRepository {

    private Map<String, LinkedList<Note>> noteMap = new ConcurrentHashMap<>();

    public boolean isUserExist(String user) {
        return noteMap.containsKey(user);
    }

    public void createUser(String user) {
        LinkedList<Note> notes = new LinkedList<>();
        noteMap.put(user, notes);
    }

    public void create(String user, Note note) {
        LinkedList<Note> notes = noteMap.get(user);
        int id = notes.getLast().getId();
        id++;
        note.setId(id);
        notes.add(note);
        noteMap.put(user, notes);
    }

    public void delete(String user, Note note) {
        List<Note> notes = noteMap.get(user);
        for (Note i : notes) {
            if (i.getId() == note.getId()) {
                notes.remove(i);
                break;
            }
        }
    }
}
