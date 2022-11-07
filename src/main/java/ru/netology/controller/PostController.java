package ru.netology.controller;

import com.google.gson.Gson;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.all();
        writeDataResponse(response, new Gson().toJson(data));
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.getById(id);
        writeDataResponse(response, new Gson().toJson(data));
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);
        final var data = service.save(post);
        writeDataResponse(response, gson.toJson(data));
    }

    private static void writeDataResponse(HttpServletResponse response, String data) throws IOException {
        response.getWriter().print(data);
    }

    public void removeById(long id, HttpServletResponse response) throws IOException {
        service.removeById(id);
        writeDataResponse(response, String.format("Delete post with id = %d, if it is!", id));
    }
}
