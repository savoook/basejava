package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private final LocalDate startTime;
    private final LocalDate finishTime;
    private final String position;
    private final String description;

    public Period(LocalDate startTime, LocalDate finishTime, String position, String description) {
        Objects.requireNonNull(startTime, "startTime must not be null");
        Objects.requireNonNull(finishTime, "finishTime must not be null");
        Objects.requireNonNull(position, "position must not be null");
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.position = position;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return startTime.equals(period.startTime) &&
                finishTime.equals(period.finishTime) &&
                position.equals(period.position) &&
                Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, finishTime, position, description);
    }

    @Override
    public String toString() {
        String des = description == null ? ", description='" + description : "";
        return "Period{" +
                "startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", position='" + position + '\'' +
                des
                + '\'' +
                '}';
    }
}
