package ru.simukov.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.simukov.springcourse.dao.PersonDAO;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //получим всех людей из DAO и передадим на отображение в представление
    @GetMapping()
    public String index(Model model) {
        //под ключем people будет лежать список из людей
        model.addAttribute("people", personDAO.index());//получим людей и положим их в модель чтобы передать их в представление
        return "people/index";//вернем название того шаблона который будет отображать список из людей
    }

    //получим одного человека по его id из DAO и передадим на отображение в представление
    @GetMapping("/{id}")//во время запска приложения id поместиться в аргументы метода
    public String show(@PathVariable("id") int id, Model model) {//с помощью аннотации PathVariable извлечем id из URL и получим к нему доступ внутри метода
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

}
