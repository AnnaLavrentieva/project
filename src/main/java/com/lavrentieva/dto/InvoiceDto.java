package com.lavrentieva.dto;

import com.lavrentieva.model.InvoiceType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class InvoiceDto {
    private String invoiceNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;
    private InvoiceType invoiceType;
    private double sum;
}
