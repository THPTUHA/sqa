package com.vaigay.WebSpringBoot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vaigay.WebSpringBoot.Entity.Course;
import com.vaigay.WebSpringBoot.Entity.Major;
import com.vaigay.WebSpringBoot.Entity.User;
import com.vaigay.WebSpringBoot.Respository.UserRepository;
import com.vaigay.WebSpringBoot.Service.ServiceUser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class InfoService {
    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetAllUser() {
        List<User> listUsers = serviceUser.getAllUser();
        assertNotNull(listUsers);
        assertTrue(listUsers.size() > 0);
    }

    @Test
    public void testListAllCourse() {
        List<Course> courses = serviceUser.listAllCourse();
        assertNotNull(courses);
        assertTrue(courses.size() > 0);
    }

    @Test
    public void testListAllMajor() {
        List<Major> majors = serviceUser.listAllMajor();
        assertNotNull(majors);
        assertTrue(majors.size() > 0);
    }

    @Test
    public void testGetUserByInvalidId() {
        try {
            User u = serviceUser.getUserById(150);
            assertNull(u);
        }catch (Exception e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }


}
