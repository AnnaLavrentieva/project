package com.lavrentieva.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter

public class ItemDto {
    private int number;
    private String name;
    private String serialNumber;
    private String inventoryNumber;
    private int productionYear;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deploymentDate;
    private int amount;
    private double price;
    private String wareGroup;
    private String person;
    private String warehouse;
}
