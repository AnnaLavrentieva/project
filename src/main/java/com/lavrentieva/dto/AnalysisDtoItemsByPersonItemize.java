package com.lavrentieva.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class AnalysisDtoItemsByPersonItemize extends AnalysisDtoItemsByPerson {
    private String wareGroupName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deploymentDate;
    private String wareName;
    private String wareId;

    public AnalysisDtoItemsByPersonItemize(String personName, String warehouseName, String wareGroupName,
                                           Date deploymentDate, String wareName, long countOfWares,
                                           String wareId) {
        super(personName, warehouseName, countOfWares);
        this.wareGroupName = wareGroupName;
        this.deploymentDate = deploymentDate;
        this.wareName = wareName;
        this.wareId = wareId;
    }
}
