package pl.coderslab.dao;

import pl.coderslab.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public interface BookCustomRepository {


    public void saveBook(Book entity);

    public Book findById(long id);

    public void update(Book entity);

    public void deleteMy(Book entity);

    public List<Book> findAll();

    public List<Book> findAllProps();

    public void resetRating(Double rating);
}
