package com.lavrentieva.service;

import com.lavrentieva.model.Invoice;
import com.lavrentieva.model.Item;
import com.lavrentieva.repository.InvoiceRepository;
import com.lavrentieva.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional
    public void addItemsAndSave(final Invoice invoice) {
        invoiceRepository.save(invoice);
    }


}
