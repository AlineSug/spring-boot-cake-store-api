package com.cakes.store.cake;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cake")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private  Flavour flavour;

    @Enumerated(EnumType.STRING)
    private Type type;

    private double price;
}
