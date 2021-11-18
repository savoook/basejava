package com.basejava.webapp.model;

import java.util.Objects;

public class StringSection extends AbstractSection {

    private String description;

    public StringSection(String description) {
        Objects.requireNonNull(description, "description must not be null");
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringSection that = (StringSection) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return description;
    }
}
