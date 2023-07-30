package com.lavrentieva.controller;

import com.lavrentieva.model.Person;
import com.lavrentieva.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ModelAndView startPage(ModelAndView modelAndView) {
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView homePage(ModelAndView modelAndView) {
        modelAndView.setViewName("menu");
        return modelAndView;
    }

    @GetMapping("/person")
    public ModelAndView showProductForm(ModelAndView modelAndView) {
//        modelAndView.addObject("person", new Person()); переробити на дто
        modelAndView.setViewName("personView");
        return modelAndView;
    }

    @PostMapping("/person")
    public ModelAndView addNewPerson(@ModelAttribute("person") Person person) {
        personService.save(person);
        ModelAndView modelAndView = new ModelAndView("redirect:/person/all");
        return modelAndView;
    }

    @GetMapping("/person/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView showAll(ModelAndView modelAndView) {
        modelAndView.addObject("people", personService.getAll());
        modelAndView.setViewName("peopleView");
        return modelAndView;
    }

    @GetMapping("/person/{id}")
    public String showPerson(@PathVariable("id") String id, Model model) {
        model.addAttribute("person", personService.getById(id));
        return "personByIdView";
//        return modelAndView;
    }

//    @GetMapping("(/person/{id}")
//    public ModelAndView showPerson(@PathVariable("id") String id, @Valid Person person,
//                                   BindingResult bindingResult, ModelAndView modelAndView) {
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("error");
//            modelAndView.addObject("message", "Validation error");
//        } else {
//            try {
//                person = personService.getById(id);
//                modelAndView.addObject("person", person);
//                modelAndView.setViewName("personByIdView");
//            } catch (NoSuchElementException e) {
//                modelAndView.setViewName("error");
//                modelAndView.addObject("message", "Person not found");
//            }
//        }
//        return modelAndView;
//    }

//    @PutMapping("(/person/{id}")
//    /update
//
//    @DeleteMapping("(/person/{id}")
//    /delete
}
