package pl.coderslab.model;


import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import pl.coderslab.validator.BookGroup;
import pl.coderslab.validator.PropositionGroup;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 150)
    @Size(min = 2, groups = {BookGroup.class, PropositionGroup.class})
    private String title;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @NotEmpty(groups = BookGroup.class)
    private List<Author> authors = new ArrayList<>();

    @Column(precision = 4, scale = 2)
    @Min(value = 1, groups = BookGroup.class)
    @Max(value = 10, groups = BookGroup.class)
    private double rating;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Publisher publisher;

    @Column(length = 600)
    @Size(max = 600, groups = {PropositionGroup.class, BookGroup.class})
    @NotEmpty(groups = PropositionGroup.class)
    private String description;

    @Min(value = 1, groups = BookGroup.class)
    private int pages;

    @AssertTrue(groups = PropositionGroup.class)
    @AssertFalse(groups = BookGroup.class)
    private boolean proposition;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    public Book() {
    }

    public Book(String title, List<Author> authors, Publisher publisher, String description, Category category) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.description = description;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isProposition() {
        return proposition;
    }

    public void setProposition(boolean proposition) {
        this.proposition = proposition;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", rating=" + rating +
                ", publisher=" + publisher +
                ", description='" + description + '\'' +
                ", pages=" + pages +
                ", proposition=" + proposition +
                ", category=" + category +
                '}';
    }

    public String propositionToString() {
        return "Proposition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
