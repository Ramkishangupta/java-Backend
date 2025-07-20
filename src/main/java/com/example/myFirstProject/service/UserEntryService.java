package com.example.myFirstProject.service;

import com.example.myFirstProject.Repository.UserEntryRepository;
import com.example.myFirstProject.entry.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    private static final Logger logger = LoggerFactory.getLogger(UserEntryService.class);

    public void saveUser(User user) {
        userEntryRepository.save(user);
    }

    public void saveNewUser(User user) {
        try {
            user.setUserName(user.getUserName());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserRoles(Arrays.asList("USER"));
            userEntryRepository.save(user);
        }catch(Exception e){
            log.info("hahhhhhhh");

        }
    }

    public List<User> getAll() {
        return userEntryRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userEntryRepository.findById(id.toHexString());
    }

    public void deleteById(ObjectId id) {
        userEntryRepository.deleteById(id.toHexString());
    }

    public User findByUserName(String userName) {
        return userEntryRepository.findByUserName(userName);
    }

    public void saveAdmin(User user) {
        user.setUserName(user.getUserName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRoles(Arrays.asList("ADMIN"));
        userEntryRepository.save(user);
    }

}
