package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

    @Entity
    @Table(name = "invoices")
    @Getter
    @Setter
    @ToString
    public class Invoice {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "invoice_id")
        private String id;

        @Column(name = "invoice_number")
        private String invoiceNumber;

        @Column(name = "invoice_date")
        @Temporal(TemporalType.DATE)
        private Date invoiceDate;

        @Column(name = "posting_date")
        @Temporal(TemporalType.DATE)
        private Date postingDate;

        @Column(name = "invoice_type")
        @Enumerated(EnumType.STRING)
        private InvoiceType invoiceType;

        @OneToMany(mappedBy = "invoice", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
                fetch = FetchType.EAGER)
        @ToString.Exclude
        private Set<Item> items = new LinkedHashSet<>();

        public void setItems (final Set<Item> items){
            items.forEach(item -> item.setInvoice(this));
        }

    }