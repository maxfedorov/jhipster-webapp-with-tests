package io.github.maxfedorov.webapplication.service.dto;

public class TodoDTO {

    private Long id;

    private String title;

    public Long getId() {
        return id;
    }

    public TodoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TodoDTO setTitle(String title) {
        this.title = title;
        return this;
    }
}
