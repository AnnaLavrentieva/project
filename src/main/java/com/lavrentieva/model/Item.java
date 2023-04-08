package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@DiscriminatorValue("item")
@Getter
@Setter
@ToString
public class Item extends Ware {

    @Column(name = "serial_number")
    private String serialNumber; //заводський номер, може не бути

    @Column(name = "inventory_number")
    private String inventoryNumber; //номенклатурний номер, може бути однаковим для кількох товарів

    @Column(name = "production_year")
    private int productionYear;

    @Column(scale=2)
    private double price;

    @ManyToOne()
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

}