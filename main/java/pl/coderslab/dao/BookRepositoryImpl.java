package pl.coderslab.dao;


import org.springframework.stereotype.Component;
import pl.coderslab.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class BookRepositoryImpl implements BookCustomRepository {


    @PersistenceContext
    EntityManager entityManager;

    public void saveBook(Book entity) {
        entityManager.persist(entity);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void update(Book entity) {
        entityManager.merge(entity);
    }

    public void deleteMy(Book entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity)); }

    public List<Book> findAll() {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.proposition = false");
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllProps() {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.proposition = true");
        List<Book> books = query.getResultList();
        return books;
    }

    @Override
    public void resetRating(Double rating) {
        Query query = entityManager.createQuery("UPDATE Book b SET b.rating = :rating");
        query.setParameter("rating", rating);
        query.executeUpdate();
    }
}
