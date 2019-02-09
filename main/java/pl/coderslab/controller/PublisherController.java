package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Publisher;
import pl.coderslab.repository.PublisherRepository;

import java.util.List;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherDao publisherDao;

    @Autowired
    private PublisherRepository publisherRepository;

    @RequestMapping(value = "/add", produces = "text/html; charset=utf-8")
    @ResponseBody
    public String hello() {
        Publisher publisher = new Publisher();
        publisher.setName("Itaka");
        publisherDao.savePublisher(publisher);
        return "Id dodanego wydawcy to:"
                + publisher.getId();
    }

    @RequestMapping(value = "/edit/{id}", produces = "text/html; charset=utf-8")
    @ResponseBody
    public String edit(@PathVariable int id) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName("Nowy wydawca");
        publisherDao.update(publisher);
        return "Zmieniłeś dane wydawcy o id: "
                + id + " na: " + publisher.getName();
    }

    @RequestMapping(value = "/find/{id}", produces = "text/html; charset=utf-8")
    @ResponseBody
    public String find(@PathVariable int id) {
        Publisher publisher = publisherDao.findById(id);
        return "Wyszukiwany wydawca to: "
                + publisher.toString();
    }

    @RequestMapping(value = "/delete/{id}", produces = "text/html; charset=utf-8")
    @ResponseBody
    public String delete(@PathVariable int id) {
        publisherDao.delete(publisherDao.findById(id));
        return "Id usuniętego wydawcy to:"
                + id;
    }


    @RequestMapping("/findByNip/{nip}")
    @ResponseBody
    public String findByNip(@PathVariable String nip) {
        List<Publisher> publishers = publisherRepository.findByNip(nip);
        return publishers.toString();
    }


    @RequestMapping("/findByRegon/{regon}")
    @ResponseBody
    public String findByRegon(@PathVariable String regon) {
        List<Publisher> publishers = publisherRepository.findByRegon(regon);
        return publishers.toString();
    }
}
