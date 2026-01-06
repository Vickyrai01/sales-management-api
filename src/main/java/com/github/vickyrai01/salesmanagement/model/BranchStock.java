package com.github.vickyrai01.salesmanagement.model;


import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BranchStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Branch branch;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;

    private Integer quantity;
}
