package com.lavrentieva.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ItemDtoCreate extends ItemDtoMovement {

    private String serialNumber;
    private String inventoryNumber;
    private int productionYear;
    private double price;
}
