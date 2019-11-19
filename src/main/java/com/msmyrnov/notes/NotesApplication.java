package com.msmyrnov.notes;

import com.msmyrnov.notes.repository.NoteRepository;
import com.msmyrnov.notes.service.NoteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({NoteService.class, NoteRepository.class})
public class NotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}

}
