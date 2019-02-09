package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    List<Author> findByEmail(String email);
    List<Author> findByPesel(String pesel);
    List<Author> findByLastName(String lastName);

    @Query("select a from Author a where a.email like ?1%")
    List<Author> queryFindByEmailStartsWith(String start);

    @Query("select a from Author a where a.pesel like ?1%")
    List<Author> queryFindByPeselStartsWith(String start);

}
