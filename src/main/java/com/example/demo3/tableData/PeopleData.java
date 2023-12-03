package com.example.demo3.tableData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PeopleData {
    private int id;
    private String firstName;
    private String lastName;
    private String patherName;
    private int groupId;
    private String type;
}
