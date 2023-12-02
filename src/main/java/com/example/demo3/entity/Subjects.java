package com.example.demo3.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "SUBJECTS")
public class Subjects {
    @Id
    @GeneratedValue
    private int id;

    private String name;
}
