package com.lavrentieva.controller;

//import com.lavrentieva.dto.PriceUpdate;
import com.lavrentieva.model.Item;
import com.lavrentieva.service.ItemService;
import com.lavrentieva.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;
    private final PersonService personService;

    @Autowired
    public ItemController(ItemService itemService, PersonService personService) {
        this.itemService = itemService;
        this.personService = personService;
    }
    @GetMapping
    public ModelAndView showItemForm (ModelAndView modelAndView){
        final Item item = new Item();
        item.setAmount(1);
        item.setDeploymentDate(new Date());
        item.setPerson(personService.getById("5f9ab1f3-ce63-4412-a0a1-83cb962bce73"));
        modelAndView.addObject("item",item);
        modelAndView.setViewName("itemForm");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addItemToCache (@ModelAttribute ("item") Item item) {
        itemService.addToCache(item);
        ModelAndView modelAndView = new ModelAndView("redirect:/item");
        return modelAndView;
    }
//
//    @GetMapping
//    public Iterable<Item> getItems() {
//        //можна повертати DTO наприклад назва та наявність та інше
//        return itemService.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public Item getItem(@PathVariable final String id){
//        Objects.requireNonNull(id);
//        return itemService.getById(id).
//                orElseThrow(()->new IllegalArgumentException("Not found item by id " + id));
//    }
//
//    @PostMapping
//    public String create (@RequestBody final Item item) {
//        return itemService.save(item);
//    }
//
//    @PutMapping("/{id}")
//    public void updatePrice (@RequestBody PriceUpdate priceUpdate, @PathVariable final String id) {
//itemService.updatePrice(id,priceUpdate.getPrice());
//    }
//
//@DeleteMapping
//    public String delete(@RequestParam final String id){
//    Objects.requireNonNull(id);
//    return itemService.delete(id);
//}
//
}
