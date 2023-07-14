package com.lavrentieva.controller;

import com.lavrentieva.dto.AnalysisDtoItemsByPerson;
import com.lavrentieva.dto.AnalysisDtoItemsByPersonItemize;
import com.lavrentieva.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/analysis")
public class AnalysisController {
    private final PersonService personService;

    @Autowired
    public AnalysisController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/item")
    public ModelAndView showOptionsForItem(ModelAndView modelAndView) {
        modelAndView.setViewName("itemAnalysisOptions");
        return modelAndView;
    }

    @GetMapping("/item/person")
    public ModelAndView summaryItemByPerson(@RequestParam(required = false) String keyword,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "3") int size,
                                            ModelAndView modelAndView) {
        final Page<AnalysisDtoItemsByPerson> itemsPage = personService.getItemsByPerson(page, size, keyword);
        final List<AnalysisDtoItemsByPerson> items = itemsPage.getContent();
        modelAndView.addObject("items", items);
        modelAndView.addObject("currentPage", itemsPage.getNumber() + 1);
        modelAndView.addObject("totalItems", itemsPage.getTotalElements());
        modelAndView.addObject("totalPages", itemsPage.getTotalPages());
        modelAndView.addObject("pageSize", size);
        modelAndView.setViewName("itemSummaryByPerson");
        return modelAndView;
    }

    @GetMapping("/item/person/{id}")
    public ModelAndView itemizedItemByPerson(@PathVariable("id") String id,
                                             @RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "3") int size,
                                             ModelAndView modelAndView) {
        Objects.requireNonNull(id);
        final Page<AnalysisDtoItemsByPersonItemize> itemsPage =
                personService.findItemsByPersonId(page, size, id);
        final List<AnalysisDtoItemsByPersonItemize> items = itemsPage.getContent();
        modelAndView.addObject("items", items);
        modelAndView.addObject("currentPage", itemsPage.getNumber() + 1);
        modelAndView.addObject("totalItems", itemsPage.getTotalElements());
        modelAndView.addObject("totalPages", itemsPage.getTotalPages());
        modelAndView.addObject("pageSize", size);
        modelAndView.setViewName("itemItemizedByPerson");
        return modelAndView;
    }

}
