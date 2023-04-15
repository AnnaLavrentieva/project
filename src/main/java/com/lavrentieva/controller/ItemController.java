package com.lavrentieva.controller;


import com.lavrentieva.dto.ItemDtoCreate;
import com.lavrentieva.service.PersonService;
import com.lavrentieva.service.WarehouseService;
import com.lavrentieva.serviceDto.ItemDtoCreateService;
import com.lavrentieva.service.WareGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/item")
public class ItemController {
    private final ItemDtoCreateService itemDTOCreateService;
    private final WareGroupService wareGroupService;
    private final WarehouseService warehouseService;
    private final PersonService personService;

    @Autowired
    public ItemController(ItemDtoCreateService itemDTOCreateService, WareGroupService wareGroupService,
                          WarehouseService warehouseService, PersonService personService) {
        this.itemDTOCreateService = itemDTOCreateService;
        this.wareGroupService = wareGroupService;
        this.warehouseService = warehouseService;
        this.personService = personService;
    }

    @GetMapping
    public ModelAndView showForm(ModelAndView modelAndView) {
        final ItemDtoCreate item = new ItemDtoCreate();
        item.setId(UUID.randomUUID().toString());
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
    public ModelAndView addToCache(@ModelAttribute("item") ItemDtoCreate item, ModelAndView modelAndView) {
        itemDTOCreateService.addToCache(item);
        modelAndView.setViewName("redirect:/item");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView showCache(ModelAndView modelAndView) {
        final List<ItemDtoCreate> itemsFromCache = itemDTOCreateService.getAllFromCache();
        final double totalSum = itemDTOCreateService.getTotalSumFromCache();
        modelAndView.addObject("items", itemsFromCache);
        modelAndView.addObject("sum", totalSum);
        modelAndView.setViewName("listItems");
        return modelAndView;
    }

    @DeleteMapping("/list/delete/{id}")
    public ModelAndView deleteFromCache(@PathVariable("id") String id, ModelAndView modelAndView) {
        itemDTOCreateService.deleteFromCache(id);
        final List<ItemDtoCreate> itemsFromCache = itemDTOCreateService.getAllFromCache();
        modelAndView.addObject("items", itemsFromCache);
        modelAndView.setViewName("redirect:/item/list");
        return modelAndView;
    }

    @PutMapping("/list/edit/{id}")
    public ModelAndView editInCache(@PathVariable("id") String id, ModelAndView modelAndView) {
        Objects.requireNonNull(id);
        final ItemDtoCreate item = itemDTOCreateService.getFromCache(id);
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
