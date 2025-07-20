package com.example.myFirstProject.Repository;

import com.example.myFirstProject.entry.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {

}
