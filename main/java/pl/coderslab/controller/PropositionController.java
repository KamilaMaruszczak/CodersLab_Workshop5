package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.validator.PropositionGroup;

import javax.validation.Validator;
import java.util.List;

@Controller
@RequestMapping("/proposition")
public class PropositionController {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherDao publisherDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private Validator validator;


    @ModelAttribute("publishers")
    public List<Publisher> getPublishers() {
        return publisherDao.getAllPublisher();
    }

    @ModelAttribute("authors")
    public List<Author> getAuthors() {
        return authorDao.getAllAuthors();
    }

    @RequestMapping(value = "/add", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "/book/add";
    }

    @RequestMapping(value = "/add", produces = "text/html; charset=utf-8", method = {RequestMethod.POST})
    public String add(@Validated({PropositionGroup.class}) Book book, BindingResult result) {

        if (result.hasErrors()) {
            return "/book/add";
        }
        bookRepository.saveBook(book);
        return "redirect:/proposition/all";
    }

    @RequestMapping(value = "/all", produces = "text/html; charset=utf-8")
    public String viewBooks(Model model) {
        List<Book> books = bookRepository.findAllProps();
        model.addAttribute("books", books);
        return "/book/allProps";
    }


    @RequestMapping(value = "/edit/{id}", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        Book book = bookRepository.findById(id);
        model.addAttribute("book", book);
        return "/book/add";
    }

    @RequestMapping(value = "/edit/{id}", produces = "text/html; charset=utf-8", method = RequestMethod.POST)
    public String update(@Validated({PropositionGroup.class}) Book book, BindingResult result /**, @PathVariable int id, Model model**/) {

        if (result.hasErrors()) {
            return "/book/add";
        }
        bookRepository.update(book);
        return "redirect:/proposition/all";
    }


    @RequestMapping(value = "/delete/{id}", produces = "text/html; charset=utf-8")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("type", "proposition");
        return "book/confirm";
    }

    @RequestMapping(value = "/deleteconfirm/{id}", produces = "text/html; charset=utf-8")
    public String confirm(@PathVariable int id) {
        bookRepository.delete(bookRepository.findById(id));
        return "redirect:/proposition/all";
    }


}