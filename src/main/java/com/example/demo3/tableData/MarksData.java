package com.example.demo3.tableData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class MarksData {
    private int id;
    private int studentId;
    private int subjectId;
    private int teacherId;
    private int groupId;
    private int mark;
    private LocalDate date;
}
