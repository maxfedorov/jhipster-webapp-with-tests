package io.github.maxfedorov.webapplication.service;

import io.github.maxfedorov.webapplication.domain.Todo;
import io.github.maxfedorov.webapplication.domain.User;
import io.github.maxfedorov.webapplication.repository.TodoRepository;
import io.github.maxfedorov.webapplication.repository.UserRepository;
import io.github.maxfedorov.webapplication.security.SecurityUtils;
import io.github.maxfedorov.webapplication.service.dto.TodoDTO;
import io.github.maxfedorov.webapplication.service.mapper.TodoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class TodoService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoService(UserRepository userRepository, TodoRepository todoRepository, TodoMapper todoMapper) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public List<TodoDTO> findAllForCurrentUser() {
        User user = getUserWithAuthorities().orElseThrow(() -> new RuntimeException("Can't find authorized user"));
        List<Todo> todos = todoRepository.findAllByUser(user);
        return todos.stream()
            .map(todoMapper::todoToDto)
            .collect(Collectors.toList());
    }

    public TodoDTO create(TodoDTO dto) {
        User user = getUserWithAuthorities()
            .orElseThrow(() -> new NullPointerException("Can't find authorized user"));

        final Todo todo = todoMapper.dtoToTodo(dto);
        todo.setUser(user);
        final Todo created = todoRepository.save(todo);

        return todoMapper.todoToDto(created);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
    }
}
