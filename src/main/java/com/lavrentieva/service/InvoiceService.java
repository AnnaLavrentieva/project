package com.lavrentieva.service;

import com.lavrentieva.model.Invoice;
import com.lavrentieva.model.Item;
import com.lavrentieva.repository.InvoiceRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ItemService itemService;
    private final WareMovementRecordService wareMovementRecordService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, ItemService itemService,
                          WareMovementRecordService wareMovementRecordService) {
        this.invoiceRepository = invoiceRepository;
        this.itemService = itemService;
        this.wareMovementRecordService = wareMovementRecordService;
    }

    @Transactional
    public void addItemsAndSave(@NonNull final Invoice invoice, @NonNull List<Item> itemList) {
        invoice.setItems(itemList);
        invoiceRepository.save(invoice);
        itemService.saveAllFromList(itemList);
        wareMovementRecordService.createAddForItemListByArrivalAndSave(itemList);
    }

}
