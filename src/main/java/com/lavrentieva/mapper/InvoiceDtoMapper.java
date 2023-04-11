package com.lavrentieva.mapper;

import com.lavrentieva.dto.InvoiceDto;
import com.lavrentieva.dto.ItemDto;
import com.lavrentieva.model.Invoice;
import com.lavrentieva.model.Item;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDtoMapper {

    public Invoice mapToModelFromDTO(InvoiceDto invoiceDto) {
        final Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(invoiceDto.getInvoiceNumber());
        invoice.setInvoiceDate(invoiceDto.getInvoiceDate());
        invoice.setInvoiceType(invoiceDto.getInvoiceType());
        invoice.setSum(invoiceDto.getSum());
        return invoice;
    }
}