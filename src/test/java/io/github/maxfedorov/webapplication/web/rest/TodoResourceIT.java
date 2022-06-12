package io.github.maxfedorov.webapplication.web.rest;

import io.github.maxfedorov.webapplication.IntegrationTest;
import io.github.maxfedorov.webapplication.service.TodoService;
import io.github.maxfedorov.webapplication.service.UserService;
import io.github.maxfedorov.webapplication.service.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.testcontainers.shaded.org.hamcrest.Matchers.hasItems;

/**
 * Integration tests for {@link UserService}.
 */
@AutoConfigureMockMvc
@IntegrationTest
@Transactional
class TodoResourceIT {
    @MockBean
    private TodoService todoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void shouldGetAllTodos() throws Exception {
        final TodoDTO firstTodo = new TodoDTO()
            .setTitle(RandomStringUtils.randomAlphabetic(10));
        final TodoDTO secondTodo = new TodoDTO()
            .setTitle(RandomStringUtils.randomAlphabetic(10));
        when(todoService.findAllForCurrentUser())
            .thenReturn(Arrays.asList(firstTodo, secondTodo));

        mockMvc
            .perform(get("/api/todo").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].title").value(hasItems(
                firstTodo.getTitle(),
                secondTodo.getTitle()
            )));
    }
}
