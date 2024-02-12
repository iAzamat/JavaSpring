package com.example.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {
    @Autowired
    private ReaderReposiroty readerRepository;

    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    public Reader findById(Long id) {
        return readerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reader not found"));
    }

    public Reader save(Reader reader) {
        return readerRepository.save(reader);
    }
}
