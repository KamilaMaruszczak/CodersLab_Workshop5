package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Publisher;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {

    List<Publisher> findByNip(String nip);
    List<Publisher> findByRegon(String regon);
}
