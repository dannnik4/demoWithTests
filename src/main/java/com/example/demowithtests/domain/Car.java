package com.example.demowithtests.domain;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "car_has_active")
    private Boolean carHasActive = Boolean.TRUE;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
}