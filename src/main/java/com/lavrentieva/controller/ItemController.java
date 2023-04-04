//package com.lavrentieva.controller;
//
//import com.lavrentieva.dto.PriceUpdate;
//import com.lavrentieva.model.Item;
//import com.lavrentieva.service.ItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/items")
//public class ItemController {
//    private final ItemService itemService;
//
//    @Autowired
//    public ItemController(ItemService itemService) {
//        this.itemService = itemService;
//    }
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
//}
