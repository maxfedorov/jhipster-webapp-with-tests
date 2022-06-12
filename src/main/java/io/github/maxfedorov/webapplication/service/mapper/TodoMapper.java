package io.github.maxfedorov.webapplication.service.mapper;

import io.github.maxfedorov.webapplication.domain.Todo;
import io.github.maxfedorov.webapplication.service.dto.TodoDTO;
import org.springframework.stereotype.Service;

@Service
public class TodoMapper {

    public TodoDTO todoToTodoDTO(final Todo todo) {
        TodoDTO dto = new TodoDTO();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        return dto;
    }
}
