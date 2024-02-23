package ru.gb.spring.testing.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.spring.testing.database.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
