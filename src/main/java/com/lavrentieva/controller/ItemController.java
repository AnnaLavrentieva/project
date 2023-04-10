package com.lavrentieva.controller;

//import com.lavrentieva.dto.PriceUpdate;

import com.lavrentieva.dto.ItemDto;
import com.lavrentieva.serviceDto.ItemDTOService;
import com.lavrentieva.service.WareGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.metadata.ManagedOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    private final ItemDTOService itemDTOService;
    private final WareGroupService wareGroupService;
    private int number = 1;

    @Autowired
    public ItemController(ItemDTOService itemDTOService, WareGroupService wareGroupService) {
        this.itemDTOService = itemDTOService;
        this.wareGroupService = wareGroupService;
    }

    @GetMapping
    public ModelAndView showItemForm(ModelAndView modelAndView) {
        final ItemDto item = new ItemDto();
        item.setNumber(number);
        item.setDeploymentDate(new Date());
        item.setProductionYear(2022);
        final List<String> wareGroups = wareGroupService.getAllWareGroupsNames();
        modelAndView.addObject("item", item);
        modelAndView.addObject("wareGroups", wareGroups);
        modelAndView.setViewName("itemForm");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addItemToCache(@ModelAttribute("item") ItemDto item, ModelAndView modelAndView) {
        itemDTOService.addToCache(item);
        number += 1;
        modelAndView.setViewName("redirect:/item"); //подивитись потім може зручніше просто на "itemForm"
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView showCacheList(ModelAndView modelAndView) {
        final List<ItemDto> itemsFromCache = itemDTOService.getAllFromCache();
        final double totalSum = itemDTOService.getTotalSumFromCache();
        modelAndView.addObject("items", itemsFromCache);
        modelAndView.addObject("sum", totalSum);
        modelAndView.setViewName("listItems");
        return modelAndView;
    }

    @DeleteMapping("/list/delete/{number}")
    public ModelAndView deleteItemFromCache(@PathVariable("number") int number, ModelAndView modelAndView) {
        itemDTOService.deleteFromCache(number);
        final List<ItemDto> itemsFromCache = itemDTOService.getAllFromCache();
        modelAndView.addObject("items", itemsFromCache);
        modelAndView.setViewName("listItems");
        return modelAndView;
    }

    @PutMapping("/list/edit/{number}")
    public ModelAndView editItemFromCache(@PathVariable("number") int number, ModelAndView modelAndView) {
        final ItemDto item = itemDTOService.getFromCache(number);
        final List<String> wareGroups = wareGroupService.getAllWareGroupsNames();
        modelAndView.addObject("item", item);
        modelAndView.addObject("wareGroups", wareGroups);
        modelAndView.setViewName("itemForm");
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
