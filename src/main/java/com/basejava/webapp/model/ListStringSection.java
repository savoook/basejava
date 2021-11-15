package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListStringSection extends AbstractSection {

    private List<String> descriptions;

    public ListStringSection() {
    }

    public ListStringSection(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListStringSection that = (ListStringSection) o;
        return Objects.equals(descriptions, that.descriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptions);
    }

    @Override
    public String toString() {
        return "" + descriptions;

    }
}
