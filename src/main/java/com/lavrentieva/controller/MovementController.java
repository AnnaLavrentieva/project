package com.lavrentieva.controller;

import com.lavrentieva.dto.MovementDto;
import com.lavrentieva.service.WareGroupService;
import com.lavrentieva.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/movement")
public class MovementController {

    private final WarehouseService warehouseService;
    private final WareGroupService wareGroupService;

    @Autowired
    public MovementController(WarehouseService warehouseService, WareGroupService wareGroupService) {
        this.warehouseService = warehouseService;
        this.wareGroupService = wareGroupService;
    }

    @GetMapping
    public ModelAndView showForm(ModelAndView modelAndView){
        modelAndView.addObject(new MovementDto());

        modelAndView.setViewName("MovementFilter");
        return modelAndView;
    }

}
