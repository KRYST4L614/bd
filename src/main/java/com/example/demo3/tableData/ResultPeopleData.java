package com.example.demo3.tableData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResultPeopleData {
    private Integer studentId;
    private String firstName;
    private String lastName;
    private Double avg;
}
