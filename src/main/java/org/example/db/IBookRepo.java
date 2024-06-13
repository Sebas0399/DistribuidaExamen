package org.example.db;

import java.util.List;

public interface IBookRepo {
    public void create(Book book);
    public Book read(Integer id);
    public void update(Integer id,Book book);
    public void delete(Integer id);
    public List<Book> readAll();
}
