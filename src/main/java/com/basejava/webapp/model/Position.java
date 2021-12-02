package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "startTime must not be null");
        Objects.requireNonNull(endDate, "finishTime must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position period = (Position) o;
        return startDate.equals(period.startDate) &&
                endDate.equals(period.endDate) &&
                title.equals(period.title) &&
                Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, title, description);
    }

    @Override
    public String toString() {
        return "Position(" + startDate + ',' + endDate + ',' + title + ',' + description + ')';
    }
}
