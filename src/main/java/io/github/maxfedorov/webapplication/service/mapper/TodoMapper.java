package io.github.maxfedorov.webapplication.service.mapper;

import io.github.maxfedorov.webapplication.domain.Todo;
import io.github.maxfedorov.webapplication.service.dto.TodoDTO;
import org.springframework.stereotype.Service;

@Service
public class TodoMapper {

    public TodoDTO todoToDto(final Todo todo) {
        TodoDTO dto = new TodoDTO();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        return dto;
    }

    public Todo dtoToTodo(final TodoDTO dto) {
        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        return todo;
    }
}
