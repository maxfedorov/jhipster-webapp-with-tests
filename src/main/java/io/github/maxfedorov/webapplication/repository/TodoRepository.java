package io.github.maxfedorov.webapplication.repository;

import io.github.maxfedorov.webapplication.domain.Todo;
import io.github.maxfedorov.webapplication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    public List<Todo> findAllByUser(User user);
}
