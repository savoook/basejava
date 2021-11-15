package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {

    private String name;
    private LocalDate startTime;
    private LocalDate finishTime;
    private String position;
    private String description;

    public Organization(String name) {
        this.name = name;
    }

    public Organization(String name, LocalDate startTime, LocalDate finishTime, String position, String description) {
        this.name = name;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.position = position;
        this.description = description;
    }

    public Organization(String name, LocalDate startTime, LocalDate finishTime, String description) {
        this.name = name;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDate finishTime) {
        this.finishTime = finishTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization organization = (Organization) o;
        return Objects.equals(name, organization.name) &&
                Objects.equals(startTime, organization.startTime) &&
                Objects.equals(finishTime, organization.finishTime) &&
                Objects.equals(position, organization.position) &&
                Objects.equals(description, organization.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startTime, finishTime, position, description);
    }

    @Override
    public String toString() {
        return
                "{ name='" + name + '\'' +
                        ", startTime=" + startTime +
                        ", finishTime=" + finishTime +
                        ", position='" + position + '\'' +
                        ", description='" + description +
                        '}';
    }
}
