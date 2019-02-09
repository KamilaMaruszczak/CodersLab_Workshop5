package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.*;
import pl.coderslab.repository.AuthorRepository;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.repository.CategoryRepository;
import pl.coderslab.repository.PublisherRepository;
import pl.coderslab.validator.BookGroup;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;


@Controller
@RequestMapping("/book")
public class BookController {

//    @Autowired
//    private Validator validator;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;


    @ModelAttribute("publishers")
    public List<Publisher> getPublishers() {
        return publisherRepository.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @RequestMapping("/hello")
    public String hello() {
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
//        return new Book("Thinking in Java","Bruce Eckel",
//                "Helion","programistyczna","programming");
        return new Book();
    }


    @RequestMapping(value = "/add", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "/book/add";
    }

    //Zakomentowany poprzedni sposób walidacji z wyświetleniem w widoku validation.jsp
    @RequestMapping(value = "/add", produces = "text/html; charset=utf-8", method = {RequestMethod.POST})
    public String add(@Validated({BookGroup.class}) Book book, BindingResult result /**,Model model**/) {


        if (result.hasErrors()) {
            return "/book/add";
        }
        bookRepository.saveBook(book);
        return "redirect:/book/all";

        //
//        model.addAttribute("type","book");
//        model.addAttribute("action","add");
//        Set<ConstraintViolation<Book>> violations = validator.validate(book);
//        if (!violations.isEmpty()) {
//            model.addAttribute("validation",violations);
//            return "/validation";
//        } else {
//            bookDao.saveBook(book);
//            return "redirect:/book/all";
//        }

    }

    @RequestMapping(value = "/all", produces = "text/html; charset=utf-8")
    public String viewBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "/book/all";
    }


    @RequestMapping(value = "/edit/{id}", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        Book book = bookRepository.findById(id);
        model.addAttribute("book", book);
        return "/book/add";
    }

    //Zakomentowany poprzedni sposób walidacji z wyświetleniem w widoku validation.jsp
    @RequestMapping(value = "/edit/{id}", produces = "text/html; charset=utf-8", method = RequestMethod.POST)
    public String update(@Validated({BookGroup.class}) Book book, BindingResult result /**, @PathVariable int id, Model model**/) {

        if (result.hasErrors()) {
            return "/book/add";
        }
        bookRepository.update(book);
        return "redirect:/book/all";

//        model.addAttribute("type","book");
//        model.addAttribute("action","edit");
//        model.addAttribute("id",id);
//        Set<ConstraintViolation<Book>> violations = validator.validate(book);
//        if (!violations.isEmpty()) {
//            model.addAttribute("validation",violations);
//            return "/validation";
//        } else {
//            bookDao.update(book);
//            return "redirect:/book/all";
//        }

    }

    @RequestMapping(value = "/find/{id}", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String find(@PathVariable int id) {
        Book book = bookRepository.findById(id);
        return "Wyszukiwana książka to: "
                + book.toString();
    }

    @RequestMapping(value = "/delete/{id}", produces = "text/html; charset=utf-8")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("type", "book");
        return "book/confirm";
    }

    @RequestMapping(value = "/deleteconfirm/{id}", produces = "text/html; charset=utf-8")
    public String confirm(@PathVariable int id) {
        bookRepository.delete(bookRepository.findById(id));
        return "redirect:/book/all";
    }


    @RequestMapping("/findByTitle")
    public String findByTitle(@RequestParam String title, Model model) {
        List<Book> books = bookRepository.findByTitleIgnoreCase(title);
        model.addAttribute("books", books);
        return "book/all";
    }


    @RequestMapping("/findByCategory/{id}")
    public String findByCategory(@PathVariable Long id, Model model) {

//        Category category = categoryRepository.findOne(id);
//        List<Book> books = bookRepository.findByCategory(category);


        List<Book> books = bookRepository.findByCategoryId(id);
        model.addAttribute("books", books);
        return "book/all";
    }

    //Zadanie Dzien4/Tworzenie zapytań 3.1
    @RequestMapping("/findByAuthor/{id}")
    public String findByAuthor(@PathVariable Long id, Model model) {

        Author author = authorRepository.findOne(id);
        List<Book> books = bookRepository.findByAuthors(author);
        model.addAttribute("books", books);
        return "book/all";
    }

    @RequestMapping("/findByAuthorName/{name}")
    public String findByAuthor(@PathVariable String name, Model model) {

        List<Book> books = bookRepository.findByAuthorsLastName(name);
        model.addAttribute("books", books);
        return "book/all";
    }


    @RequestMapping("/findByPublisher/{id}")
    public String findByPublisher(@PathVariable Long id, Model model) {

        Publisher publisher = publisherRepository.findOne(id);
        List<Book> books = bookRepository.findByPublisher(publisher);
        model.addAttribute("books", books);
        return "book/all";
    }


    @RequestMapping("/findFirstByCategoryNameSorted/{name}")
    public String findByCategoryNameSorted(@PathVariable String name, Model model) {

        List<Book> books = bookRepository.findFirstByCategoryNameOrderByTitle(name);
        model.addAttribute("books", books);
        return "book/all";
    }

    @RequestMapping("/findByTitleQuery/{title}")
    public String findByTitleQuery(@PathVariable String title, Model model) {
        List<Book> books = bookRepository.queryFindByTitle(title);
        model.addAttribute("books", books);
        return "book/all";
    }

    @RequestMapping("/findByCategoryQuery/{id}")
    public String findByCategoryQuery(@PathVariable Long id, Model model) {

        Category category = categoryRepository.findOne(id);
        List<Book> books = bookRepository.queryFindByCategory(category);
        model.addAttribute("books", books);
        return "book/all";
    }

    @RequestMapping("/findByRatingQuery/{min}/{max}")
    public String findByRatingQuery(@PathVariable Double min, @PathVariable Double max, Model model) {

        List<Book> books = bookRepository.queryFindRating(min, max);
        model.addAttribute("books", books);
        return "book/all";
    }

    @RequestMapping("/findByPublisherQuery/{id}")
    public String findByPublisherQuery(@PathVariable Long id, Model model) {

        Publisher publisher = publisherRepository.findOne(id);
        List<Book> books = bookRepository.queryFindByPublisher(publisher);
        model.addAttribute("books", books);
        return "book/all";
    }

    @RequestMapping("/findByCategorySortedQuery/{id}")
    public String findByCategorySortedQuery(@PathVariable Long id, Model model) {
        Category category = categoryRepository.findOne(id);
        List<Book> books = bookRepository.queryFindByCategorySorted(category);
        model.addAttribute("books", books);
        return "book/all";
    }

    @RequestMapping("/setRating/{value}")
    public String setRating(@PathVariable Double value) {
        bookRepository.resetRating(value);
        return "redirect:/book/all";
    }
}
