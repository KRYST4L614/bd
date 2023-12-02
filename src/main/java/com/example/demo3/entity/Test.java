package com.example.demo3.entity;


import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "TEST")
public class Test {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "val")
    private int val;
}
