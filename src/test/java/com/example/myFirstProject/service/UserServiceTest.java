package com.example.myFirstProject.service;

import com.example.myFirstProject.Repository.UserEntryRepository;
import com.example.myFirstProject.entry.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserEntryRepository userRepository;
    @Disabled
    @Test
    public void testFindByUserName(){
        assertEquals(4,2+2);
        assertNotNull(userRepository.findByUserName("ram"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,6"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b,"failed for"+a+b);
    }

}
