package com.epam.wilk.endpoints;

import com.epam.wilk.configuration.TestProperties;
import com.epam.wilk.models.Post;
import io.restassured.response.Response;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PostEndpoint extends BaseEndpoint {

    private static String POST_URI = "posts/";

    public PostEndpoint(@NonNull TestProperties testProperties) {
        super(testProperties);
    }

    public Response getPost(int postId) {
        return given()
                .get(POST_URI + postId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public Response getPosts() {
        return given()
                .get(POST_URI)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public Response addPost(int userId, String title, String body) {
        var post = new Post(userId, title, body);
        return given()
                .body(post)
                .post(POST_URI)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public Response updatePost(int id, int userId, String title, String body) {
        var post = new Post(id, userId, title, body);
        return given()
                .body(post)
                .put(POST_URI + id)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public Response deletePost(int id) {
        return given()
                .delete(POST_URI + id)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }
}
