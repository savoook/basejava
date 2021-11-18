package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListStringSection extends AbstractSection {

    private List<String> descriptions;

    public ListStringSection(List<String> descriptions) {
        Objects.requireNonNull(descriptions, "descriptions must not be null");
        this.descriptions = descriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListStringSection that = (ListStringSection) o;
        return descriptions.equals(that.descriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptions);
    }

    @Override
    public String toString() {
        return descriptions.toString();
    }
}
