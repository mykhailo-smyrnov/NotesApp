package com.msmyrnov.notes.service;

import com.msmyrnov.notes.model.Note;
import com.msmyrnov.notes.repository.NoteRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public void add(String user, Note note) {
        if (StringUtils.isBlank(user) || note == null)
            throw new IllegalArgumentException("User and Note required.");

        if (!noteRepository.isUserExist(user))
            noteRepository.createUser(user);

        noteRepository.create(user, note);
    }

    public void delete(String user, Note note) {
        if (StringUtils.isBlank(user) || note == null)
            throw new IllegalArgumentException("User and Note required.");

        noteRepository.delete(user, note);
    }
}
