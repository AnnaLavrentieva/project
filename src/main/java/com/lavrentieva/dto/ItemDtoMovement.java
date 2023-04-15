package com.lavrentieva.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Getter
@Setter
public class ItemDtoMovement {
    protected String id;
    protected String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date deploymentDate;
    protected String person;
    protected String warehouse;
    protected String wareGroup;
    protected int amount;
    private int amountForMovement;
}
