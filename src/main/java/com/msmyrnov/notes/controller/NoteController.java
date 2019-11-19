package com.msmyrnov.notes.controller;

import com.msmyrnov.notes.model.Note;
import com.msmyrnov.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/note/add")
    public HttpStatus add(@RequestAttribute Note note, HttpSession session) {
        String user = session.getId();
        noteService.add(user, note);
        return HttpStatus.OK;
    }

}
