package com.lavrentieva.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ItemDtoCreate extends ItemDtoMovement {
    private String id;
    private String serialNumber;
    private String inventoryNumber;
    private int productionYear;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deploymentDate;
    private double price;
    private String wareGroup;
}
