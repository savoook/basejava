package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class ExperienceSection extends AbstractSection {
    private List<Organization> organizations;

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienceSection that = (ExperienceSection) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

    public ExperienceSection(List<Organization> organizations) {
        this.organizations = organizations;


    }

    @Override
    public String toString() {
        return "" + organizations;
    }
}


