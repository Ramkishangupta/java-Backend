package com.example.myFirstProject.Repository;

import com.example.myFirstProject.entry.JournalEntry;
import com.example.myFirstProject.entry.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository<User, String> {
    User findByUserName(String username);
    void deleteByUserName(String username);

}
