package com.vaigay.WebSpringBoot;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.vaigay.WebSpringBoot.Respository.CourseRepository;
import com.vaigay.WebSpringBoot.Respository.MajorRepository;
import com.vaigay.WebSpringBoot.Respository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc

public class InfoController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetNewUser() throws Exception {
        int sizeMajor = (int) majorRepository.count();
        int sizeCourse = (int) courseRepository.count();

        mockMvc.perform(get("/newUser"))
                .andExpect(status().isOk())
                .andExpect(view().name("newUser"))
                .andExpect(model().attribute("listMajor", hasSize(sizeMajor)))
                .andExpect(model().attribute("listCourse", hasSize(sizeCourse)));
    }

    @Test
    public void testGetListUserWithInvalidCode() throws Exception {
        mockMvc.perform(get("/findUserByCode").param("code","xadase123123" ))
                .andExpect(status().isOk())
                .andExpect(view().name("listUser"))
                .andExpect(model().attribute("listUser", hasSize(0)));

    }

    @Test
    public void testGetListUserWithValidCode() throws Exception {
        mockMvc.perform(get("/findUserByCode").param("code","B17DCCN14" ))
                .andExpect(status().isOk())
                .andExpect(view().name("listUser"))
                .andExpect(model().attribute("listUser", hasSize(1)));
    }

    @Test
    public void testGetListUserWithEmptyCode() throws Exception {
        mockMvc.perform(get("/findUserByCode").param("code","" ))
                .andExpect(status().isOk())
                .andExpect(view().name("listUser"))
                .andExpect(model().attribute("listUser", hasSize(0)));
    }

    @Test
    public void testGetEditUserWithInvalidId() throws Exception {


        mockMvc.perform(get("/user/edit/2"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testGetEditUserWithValidId() throws Exception {
        int sizeMajor = (int) majorRepository.count();
        int sizeCourse = (int) courseRepository.count();

        mockMvc.perform(get("/user/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("editUser"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("listMajor", hasSize(sizeMajor)))
                .andExpect(model().attribute("listCourse", hasSize(sizeCourse)));
    }


}
