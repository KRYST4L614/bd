package com.example.demo3.tableData.result;

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
