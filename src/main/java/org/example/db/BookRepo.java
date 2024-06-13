package org.example.db;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
@ApplicationScoped
public class BookRepo implements IBookRepo {
    @Inject
    JPAResourceBean jpaResourceBean;

    @Override
    public void create(Book book) {
        EntityManager em=jpaResourceBean.getEMF().createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();

                  }
        finally {
            em.close();
        }
    }

    @Override
    public Book read(Integer id) {
        EntityManager em=jpaResourceBean.getEMF().createEntityManager();
        try {
            TypedQuery<Book> query=em.createQuery("Select b from Book b where b.id=:id",Book.class);
            query.setParameter("id",id);
            return query.getSingleResult();
        }
        finally {
            em.close();
        }
    }

    @Override
    public void update(Integer id,Book book) {
        EntityManager em=jpaResourceBean.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();

            Book bookEncontrado=read(id);
            bookEncontrado.setAuthor(book.getAuthor());
            bookEncontrado.setIsbn(book.getIsbn());
            bookEncontrado.setPrecio(book.getPrecio());
            bookEncontrado.setTitle(book.getTitle());
            em.merge(bookEncontrado);
            em.getTransaction().commit();

        }
        finally {
            em.close();
        }
    }

    @Override
    public void delete(Integer id) {
        EntityManager em=jpaResourceBean.getEMF().createEntityManager();
        try {

            Book bookEncontrado=this.read(id);
            System.out.println(bookEncontrado);
            em.getTransaction().begin();

            em.remove(bookEncontrado);
            em.getTransaction().commit();

        }
        finally {
            em.close();
        }
    }

    @Override
    public List<Book> readAll() {
        EntityManager em=jpaResourceBean.getEMF().createEntityManager();
        try {
            TypedQuery<Book> query=em.createQuery("Select b from Book b",Book.class);
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }
}
