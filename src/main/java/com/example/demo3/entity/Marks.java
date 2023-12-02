package com.example.demo3.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "marks")
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "group_id")
    private int groupId;

    @Column(name = "value")
    private int mark;

    @Column(name = "date")
    private LocalDate date;

    @Override
    public String toString() {
        return "Marks{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", teacherId=" + teacherId +
                ", groupId=" + groupId +
                ", mark=" + mark +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marks marks = (Marks) o;
        return id == marks.id && studentId == marks.studentId && subjectId == marks.subjectId &&
                teacherId == marks.teacherId && groupId == marks.groupId &&
                mark == marks.mark && Objects.equals(date, marks.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, subjectId, teacherId, groupId, mark, date);
    }
}