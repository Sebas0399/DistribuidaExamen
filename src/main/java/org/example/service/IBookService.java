package org.example.service;

import org.example.db.Book;

import java.util.List;

public interface IBookService {
    public void create(Book book);
    public Book read(Integer id);
    public void update(Integer id,Book book);
    public void delete(Integer id);
    public List<Book> readAll();
}