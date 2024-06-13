package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.db.Book;
import org.example.db.BookRepo;

import java.util.List;
@ApplicationScoped
@Named
public class BookService implements IBookService{
    @Inject
    BookRepo bookRepo;
    @Override
    public void create(Book book) {
        bookRepo.create(book);
    }

    @Override
    public Book read(Integer id) {
        return bookRepo.read(id);
    }

    @Override
    public void update(Integer id, Book book) {
        bookRepo.update(id,book);
    }

    @Override
    public void delete(Integer id) {
            bookRepo.delete(id);
    }

    @Override
    public List<Book> readAll() {
                return bookRepo.readAll();
    }
}
