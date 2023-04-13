package com.lavrentieva.controller;

import com.lavrentieva.dto.InvoiceDto;
import com.lavrentieva.mapper.InvoiceDtoMapper;
import com.lavrentieva.model.Invoice;
import com.lavrentieva.model.Item;
import com.lavrentieva.service.InvoiceService;
import com.lavrentieva.service.ItemService;
import com.lavrentieva.serviceDto.ItemDtoCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/invoice")
@PreAuthorize("hasAuthority('ADMIN')") //вже є у конфігурації - щоб було що копіювати )
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final ItemService itemService;
    private final ItemDtoCreateService itemDTOCreateService;
    private final InvoiceDtoMapper invoiceDtoMapper;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, ItemService itemService,
                             ItemDtoCreateService itemDTOCreateService, InvoiceDtoMapper invoiceDtoMapper) {
        this.invoiceService = invoiceService;
        this.itemService = itemService;
        this.itemDTOCreateService = itemDTOCreateService;
        this.invoiceDtoMapper = invoiceDtoMapper;
    }

    @GetMapping
    public ModelAndView showForm(ModelAndView modelAndView) {
        final InvoiceDto invoice = new InvoiceDto();
        final double sum = itemDTOCreateService.getTotalSumFromCache();
        invoice.setSum(sum);
        modelAndView.addObject("invoice", invoice);
        modelAndView.setViewName("invoiceForm");
        return modelAndView;
    }
    @PostMapping
    public ModelAndView createAndSave(@ModelAttribute("invoice") InvoiceDto invoice,
                                      ModelAndView modelAndView) {
        final Invoice invoiceCreated = invoiceDtoMapper.mapToModelFromDTO(invoice);
        final List<Item> itemList = itemDTOCreateService.mapToModelInListFromDTO();
        invoiceService.addItemsAndSave(invoiceCreated,itemList);
        itemDTOCreateService.clearCache();
        modelAndView.addObject("message", "Invoice has been saved successfully");
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }
}
