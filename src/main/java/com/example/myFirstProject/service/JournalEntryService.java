package com.example.myFirstProject.service;

import com.example.myFirstProject.Repository.JournalEntryRepository;
import com.example.myFirstProject.entry.JournalEntry;
import com.example.myFirstProject.entry.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserEntryService userService;


    @Transactional
    public void  saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error is occured");
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id.toHexString());
    }
    public boolean deleteById(ObjectId id,String userName){
        boolean removed = false;
        User user = userService.findByUserName(userName);
        removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if(removed){
            userService.saveUser(user);
            journalEntryRepository.deleteById(id.toHexString());
        }
        return removed;
    }

//    public List<JournalEntry> findByUserName(String userName){
//        User user = userRepository.findByUserName(userName);
//    }

}
//controller -->service --> repository