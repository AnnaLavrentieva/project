package com.lavrentieva.controller;


import com.lavrentieva.dto.ItemDto;
import com.lavrentieva.service.PersonService;
import com.lavrentieva.service.WarehouseService;
import com.lavrentieva.serviceDto.ItemDtoService;
import com.lavrentieva.service.WareGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    private final ItemDtoService itemDTOService;
    private final WareGroupService wareGroupService;
    private final WarehouseService warehouseService;
    private final PersonService personService;
    private int number = 1;

    @Autowired
    public ItemController(ItemDtoService itemDTOService, WareGroupService wareGroupService,
                          WarehouseService warehouseService, PersonService personService) {
        this.itemDTOService = itemDTOService;
        this.wareGroupService = wareGroupService;
        this.warehouseService = warehouseService;
        this.personService = personService;
    }

    @GetMapping
    public ModelAndView showForm(ModelAndView modelAndView) {
        final ItemDto item = new ItemDto();
        item.setNumber(number);
        item.setDeploymentDate(new Date());
        item.setProductionYear(2022);
        final List<String> wareGroups = wareGroupService.getAllWareGroupsNames();
        final List<String> warehouses = warehouseService.getAllWarehousesNames();
        final List<String> people = personService.getAllPeopleNames();
        modelAndView.addObject("item", item);
        modelAndView.addObject("wareGroups", wareGroups);
        modelAndView.addObject("warehouses", warehouses);
        modelAndView.addObject("people", people);
        modelAndView.setViewName("itemForm");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addToCache(@ModelAttribute("item") ItemDto item, ModelAndView modelAndView) {
        itemDTOService.addToCache(item);
        number += 1;
        modelAndView.setViewName("redirect:/item"); //подивитись потім може зручніше просто на "itemForm"
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView showCache(ModelAndView modelAndView) {
        final List<ItemDto> itemsFromCache = itemDTOService.getAllFromCache();
        final double totalSum = itemDTOService.getTotalSumFromCache();
        modelAndView.addObject("items", itemsFromCache);
        modelAndView.addObject("sum", totalSum);
        modelAndView.setViewName("listItems");
        return modelAndView;
    }

    @DeleteMapping("/list/delete/{number}")
    public ModelAndView deleteFromCache(@PathVariable("number") int number, ModelAndView modelAndView) {
        itemDTOService.deleteFromCache(number);
        final List<ItemDto> itemsFromCache = itemDTOService.getAllFromCache();
        modelAndView.addObject("items", itemsFromCache);
        modelAndView.setViewName("redirect:/item/list");
        return modelAndView;
    }

    @PutMapping("/list/edit/{number}")
    public ModelAndView editInCache(@PathVariable("number") int number, ModelAndView modelAndView) {
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
