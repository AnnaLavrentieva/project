//package com.lavrentieva.controller;
//
//import com.lavrentieva.model.Item;
//import com.lavrentieva.repository.ItemRepository;
//import com.lavrentieva.service.ItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping("/view/items")
//public class ViewItemController {
//    private final ItemService itemService;
//
//    @Autowired
//    public ViewItemController(ItemService itemService) {
//        this.itemService = itemService;
//    }
//
//    @GetMapping
//    public ModelAndView getItems (final ModelAndView modelAndView){
//        modelAndView.addObject("items", itemService.getAll());
//        modelAndView.setViewName("items");
//        return modelAndView;
//    }
//
//}
