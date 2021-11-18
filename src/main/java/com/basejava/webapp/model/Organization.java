package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {

    private final Link homepage;
    private final LocalDate startTime;
    private final LocalDate finishTime;
    private final String position;
    private final String description;

    public Organization(String name, String url, LocalDate startTime, LocalDate finishTime, String position, String description) {
        Objects.requireNonNull(startTime, "startTime must not be null");
        Objects.requireNonNull(finishTime, "finishTime must not be null");
        Objects.requireNonNull(position, "position must not be null");
        this.homepage = new Link(name, url);
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.position = position;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homepage, that.homepage) &&
                startTime.equals(that.startTime) &&
                finishTime.equals(that.finishTime) &&
                position.equals(that.position) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homepage, startTime, finishTime, position, description);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homepage=" + homepage +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
