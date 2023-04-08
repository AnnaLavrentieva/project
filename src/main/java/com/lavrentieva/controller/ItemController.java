package com.lavrentieva.controller;

//import com.lavrentieva.dto.PriceUpdate;
import com.lavrentieva.model.Item;
import com.lavrentieva.model.WareGroup;
import com.lavrentieva.service.ItemService;
import com.lavrentieva.service.PersonService;
import com.lavrentieva.service.WareGroupService;
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
    private final WareGroupService wareGroupService;

    @Autowired
    public ItemController(ItemService itemService, WareGroupService wareGroupService) {
        this.itemService = itemService;
        this.wareGroupService=wareGroupService;
    }
    @GetMapping
    public ModelAndView showItemForm (ModelAndView modelAndView){
        final Item item = itemService.createForInputForm();
        final Iterable<WareGroup> wareGroups = wareGroupService.getAll();
        modelAndView.addObject("item",item);
        modelAndView.addObject("wareGroups",wareGroups);
        modelAndView.setViewName("itemForm");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addItemToCache (@ModelAttribute ("item") Item item, ModelAndView modelAndView) {
        itemService.addToCache(item);
        modelAndView.setViewName("redirect:/item");
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
