package io.github.maxfedorov.webapplication.repository;

import io.github.maxfedorov.webapplication.IntegrationTest;
import io.github.maxfedorov.webapplication.domain.Todo;
import io.github.maxfedorov.webapplication.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

import static io.github.maxfedorov.webapplication.RandomUtils.randomTodo;
import static io.github.maxfedorov.webapplication.RandomUtils.randomUser;
import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
@Transactional
public class TodoRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void shouldSaveTodo() {
        final User user = randomUser();
        userRepository.saveAndFlush(user);
        final Todo todo = randomTodo(user);
        todoRepository.saveAndFlush(todo);

        List<Todo> allTodo = todoRepository.findAll();

        assertThat(allTodo).hasSize(1)
            .extracting(Todo::getTitle)
            .contains(todo.getTitle());
    }
}
