package com.basejava.webapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private String fullName;
    private final Map<Contacts, String> contacts = new HashMap<>();
    private final Map<SectionType, AbstractSection> sections = new HashMap<>();
//    private Contacts contacts;
//    private Block<String> Personal;
//    private Block<String> objective;
//    private Block<LifeCycle> experience;
//    private Block<LifeCycle> education;
//    private Block<ArrayList<String>> achievement;
//    private Block<ArrayList<String>> qualifications;


    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "UUID must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<Contacts, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + ",\n" +
                "fullName='" + fullName + ",\n" +
                "contacts=" + contacts + ",\n" +
                "sections=" + sections +
                '}';
    }

    @Override
    public int compareTo(Resume o) {
        int nameCmp = fullName.compareTo(o.getFullName());
        return nameCmp != 0 ? nameCmp : uuid.compareTo(o.getUuid());
    }
}