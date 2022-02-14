package org.example.echo.rs;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class Employee {
    private long id;
    private String name;
    private OffsetDateTime hireDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(OffsetDateTime hireDate) {
        this.hireDate = hireDate;
    }

//    public ZonedDateTime getHireDate() {
//        return hireDate;
//    }
//
//    public void setHireDate(ZonedDateTime hireDate) {
//        this.hireDate = hireDate;
//    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}
