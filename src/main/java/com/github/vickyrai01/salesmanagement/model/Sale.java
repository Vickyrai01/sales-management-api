package com.github.vickyrai01.salesmanagement.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.vickyrai01.salesmanagement.model.enums.SaleState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SaleState state;

    private Double total;

    @ManyToOne
    private Branch branch;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SaleItem> saleItemList;



}
