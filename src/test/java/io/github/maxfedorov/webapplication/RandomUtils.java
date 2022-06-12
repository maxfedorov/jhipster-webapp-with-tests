package io.github.maxfedorov.webapplication;

import io.github.maxfedorov.webapplication.domain.Todo;
import io.github.maxfedorov.webapplication.domain.User;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.util.function.Consumer;

public class RandomUtils {
    public static User randomUser() {
        return randomUser((user) -> {});
    }

    public static User randomUser(Consumer<User> consumer) {
        User user = new User();
        user.setLogin(RandomStringUtils.randomAlphabetic(10));
        user.setPassword(RandomStringUtils.randomAlphabetic(60));
        consumer.accept(user);
        return user;
    }

    public static Todo randomTodo(User user) {
        Todo todo = new Todo();
        todo.setUser(user);
        todo.setTitle(RandomStringUtils.randomAlphabetic(10));
        return todo;
    }
}
