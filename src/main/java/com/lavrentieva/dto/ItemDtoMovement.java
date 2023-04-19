package com.lavrentieva.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
public class ItemDtoMovement {
    protected String id;
    @Size(min = 3,max = 30)
    protected String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date deploymentDate;
    protected String person;
    protected String warehouse;
    protected String wareGroup;
    @NotNull
    @Positive
    protected int amount;
    private int amountForMovement;
}
