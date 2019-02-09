package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.dao.BookCustomRepository;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Category;
import pl.coderslab.model.Publisher;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long>, BookCustomRepository {

    List<Book> findByTitleIgnoreCase(String title);
    List<Book> findByCategory(Category category);
    List<Book> findByCategoryId(Long id);
    List<Book> findByAuthors(Author author);
    List<Book> findByAuthorsLastName(String lastName);
    List<Book> findByPublisher(Publisher publisher);
    List<Book> findFirstByCategoryNameOrderByTitle(String name);

    @Query("select b from Book b where b.title = ?1")
    List<Book> queryFindByTitle(String title);

    @Query("select b from Book b where b.category = :category")
    List<Book> queryFindByCategory(@Param("category") Category category);

    @Query("select b from Book b where b.rating between ?1 and ?2")
    List<Book> queryFindRating(Double min,Double max);

    @Query("select b from Book b where b.publisher = ?1")
    List<Book> queryFindByPublisher(Publisher publisher);

    @Query(value = "select * from books where category_id = ?1 order by title limit 1", nativeQuery = true)
    List<Book> queryFindByCategorySorted(Category category);
}
