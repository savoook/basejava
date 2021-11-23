package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {

    private final Link homepage;
    private final List<Period> periodList;


    public Organization(String name, String url, List<Period> periodList) {
        Objects.requireNonNull(periodList, "periodList must not be null");
        this.homepage = new Link(name, url);
        this.periodList = periodList;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homepage, that.homepage) &&
                periodList.equals(that.periodList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homepage, periodList);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homepage=" + homepage +
                ", periodList=" + periodList +
                '}';
    }
}
