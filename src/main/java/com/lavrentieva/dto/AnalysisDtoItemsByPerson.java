package com.lavrentieva.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalysisDtoItemsByPerson {
    protected String personName;
    protected String warehouseName;
    protected long countOfWares;

    public AnalysisDtoItemsByPerson(String personName, String warehouseName, long countOfWares) {
        this.personName = personName;
        this.warehouseName = warehouseName;
        this.countOfWares = countOfWares;
    }
}
