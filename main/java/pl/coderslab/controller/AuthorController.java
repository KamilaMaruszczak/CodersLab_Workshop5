package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.model.Author;
import pl.coderslab.repository.AuthorRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private Validator validator;

    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(value = "/add", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("author", new Author());
        return "/author/add";
    }

    @RequestMapping(value = "/add", produces = "text/html; charset=utf-8", method = RequestMethod.POST)
    public String add(@Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "/author/add";
        }
        authorDao.saveAuthor(author);
        return "redirect:/author/all";
    }

    @RequestMapping(value = "/all", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    public String all(Model model) {
        List<Author> authors = authorDao.getAllAuthors();
        model.addAttribute("authors", authors);
        return "/author/all";
    }

    @RequestMapping(value = "/edit/{id}", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("author", authorDao.findById(id));
        return "/author/add";
    }

    @RequestMapping(value = "/edit/{id}", produces = "text/html; charset=utf-8", method = RequestMethod.POST)
    public String edit(@Valid Author author, BindingResult result) {

        if (result.hasErrors()) {
            return "author/add";
        }
        authorDao.update(author);
        return "redirect:/author/all";
    }

    @RequestMapping(value = "/find/{id}", produces = "text/html; charset=utf-8")
    @ResponseBody
    public String find(@PathVariable int id) {
        Author author = authorDao.findById(id);
        return "Wyszukiwany autor to: "
                + author.toString();
    }

    @RequestMapping(value = "/delete/{id}", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "/author/confirm";
    }

    @RequestMapping(value = "/deleteconfirm/{id}", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        authorDao.delete(authorDao.findById(id));
        return "redirect:/author/all";
    }

    @RequestMapping("/findByEmail")
    public String findByEmail(@RequestParam String email, Model model) {
    List<Author> authors = authorRepository.findByEmail(email);
    model.addAttribute("authors", authors);
    return "author/all";
}

    @RequestMapping("/findByName/{name}")
    public String findByName(@PathVariable String name, Model model) {
        List<Author> authors = authorRepository.findByLastName(name);
        model.addAttribute("authors", authors);
        return "author/all";
    }

    @RequestMapping("/findByPesel/{pesel}")
    public String findByPesel(@PathVariable String pesel, Model model) {
        List<Author> authors = authorRepository.findByPesel(pesel);
        model.addAttribute("authors", authors);
        return "author/all";
    }

    @RequestMapping("/findByEmailStartsWith/{start}")
    public String findByEmailStartsWith(@PathVariable String start, Model model) {
        List<Author> authors = authorRepository.queryFindByEmailStartsWith(start);
        model.addAttribute("authors", authors);
        return "author/all";
    }


    @RequestMapping("/findByPeselStartsWith/{start}")
    public String findByPeselStartsWith(@PathVariable String start, Model model) {
        List<Author> authors = authorRepository.queryFindByPeselStartsWith(start);
        model.addAttribute("authors", authors);
        return "author/all";
    }

}
