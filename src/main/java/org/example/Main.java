package org.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import io.helidon.webserver.Router;
import io.helidon.webserver.Routing;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.spi.ServerFeature;
import jakarta.enterprise.inject.spi.CDI;
import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;
import org.example.db.Book;
import org.example.service.BookService;

import java.util.List;


public class Main {
    private static ContainerLifecycle lifecycle=null;

    public static void main(String[] args) {
        lifecycle=WebBeansContext.currentInstance().getService(ContainerLifecycle.class);
        lifecycle.startApplication(null);
        BookService bookService= CDI.current().select(BookService.class).get();
        Gson gson=new Gson();
        WebServer.builder()

                .port(8080)

                .routing(builder -> builder
                        .get("/books", (req, res) -> {
                        List<Book> books=bookService.readAll();
                        res.status(200).send(gson.toJson(books));
                    })
                    .get("/books/{id}", (req, res) -> {
                        String id=req.path().pathParameters().get("id");
                        Book book=bookService.read(Integer.parseInt(id));
                        res.send(gson.toJson(book));
                    })
                    .post("/books", (req, res) -> {
                        String body = req.content().as(String.class);
                        Book book = gson.fromJson(body, Book.class);
                        bookService.create(book);
                        res.send(body);
                    })
                    .put("/books/{id}", (req, res) -> {
                        String id=req.path().pathParameters().get("id");
                        String body = req.content().as(String.class);

                        Book book=req.content().as(Book.class);
                        bookService.update(Integer.parseInt(id),book);
                        res.status(200).send(gson.toJson(book));
                    })
                    .delete("/books/{id}", (req, res) -> {
                        String id=req.path().pathParameters().get("id");
                        bookService.delete(Integer.parseInt(id));
                        res.status(204).send();
                    })
                )
                .build()
                .start();



    }
    public static void shutdown(){
        lifecycle.stopApplication(null);
    }



}