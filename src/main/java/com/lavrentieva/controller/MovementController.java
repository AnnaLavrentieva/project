package com.lavrentieva.controller;

import com.lavrentieva.dto.ItemDtoMovement;
import com.lavrentieva.dto.ItemsDtoInListMovement;
import com.lavrentieva.mapper.ItemDtoMovementMapper;
import com.lavrentieva.model.Item;
import com.lavrentieva.model.Person;
import com.lavrentieva.model.Warehouse;
import com.lavrentieva.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/movement")
public class MovementController {

    private final WarehouseService warehouseService;
    private final PersonService personService;
    private final ItemService itemService;
    private final ItemDtoMovementMapper movementMapper;

    @Autowired
    public MovementController(WarehouseService warehouseService, PersonService personService,
                              ItemService itemService, ItemDtoMovementMapper movementMapper) {
        this.warehouseService = warehouseService;
        this.personService = personService;
        this.itemService = itemService;
        this.movementMapper = movementMapper;
    }

    @GetMapping
    public ModelAndView choseItem(@RequestParam(defaultValue = "All") String warehouse,
                                  @RequestParam(defaultValue = "All") String person,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "3") int size, ModelAndView modelAndView) {
        final Warehouse warehouseSelected = warehouseSelected(warehouse);
        final Person personSelected = personSelected(person);
        final Page<Item> itemPage = itemService.getPageByCondition(page, size, warehouseSelected,
                personSelected);
        final List<ItemDtoMovement> items = movementMapper.mapToDtoInListFromModel(itemPage.getContent());
        final ItemsDtoInListMovement form = new ItemsDtoInListMovement();
        form.setItems(items);
        final List<String> warehouses = warehouseService.getAllWarehousesNames();
        warehouses.add("All");
        final List<String> people = personService.getAllPeopleNames();
        people.add("All");
        modelAndView.addObject("warehouse", warehouse);
        modelAndView.addObject("person", person);
        modelAndView.addObject("warehouses", warehouses);
        modelAndView.addObject("people", people);
        modelAndView.addObject("form", form);
        modelAndView.addObject("currentPage", itemPage.getNumber() + 1);
        modelAndView.addObject("totalItems", itemPage.getTotalElements());
        modelAndView.addObject("totalPages", itemPage.getTotalPages());
        modelAndView.addObject("pageSize", size);
        modelAndView.setViewName("movement");
        return modelAndView;
    }


    @GetMapping("/list")
    public ModelAndView showItemsAndForm(@ModelAttribute("form") ItemsDtoInListMovement form,
                                         ModelAndView modelAndView) {
        final ItemsDtoInListMovement formForMovement = itemService.getListForMovement(form);
        final List<String> warehouses = warehouseService.getAllWarehousesNames();
        final List<String> people = personService.getAllPeopleNames();
        modelAndView.addObject("form", formForMovement);
        modelAndView.addObject("warehouses", warehouses);
        modelAndView.addObject("people", people);
        modelAndView.setViewName("movementList");
        return modelAndView;
    }

    @PostMapping("/list")
    public ModelAndView createAndSave(@RequestParam("warehouse") String warehouse,
                                      @RequestParam("person") String person,
                                      @ModelAttribute("form") ItemsDtoInListMovement form,
                                      ModelAndView modelAndView) {
        final Warehouse warehouseSelected = warehouseService.getById(warehouse);
        final Person personSelected = personService.getById(person);
        final List<ItemDtoMovement> itemDtoList = form.getItems();
        itemDtoList.stream()
                .forEach(itemDto -> itemService.getAndChangeOrCreate(itemDto,
                        warehouseSelected, personSelected));
        modelAndView.addObject("message", "Items have been moved successfully");
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    private Warehouse warehouseSelected(String warehouse) {
        if (warehouse.equals("All")) {
            return null;
        } else {
            return warehouseService.getById(warehouse);
        }
    }

    private Person personSelected(String person) {
        if (person.equals("All")) {
            return null;
        } else {
            return personService.getById(person);
        }
    }
}
