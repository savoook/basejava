package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public interface Storage {
    public void clear();

    public void save(Resume resume);

    public void update(Resume resume);

    public Resume get(String uuid);

    public void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll();

    public int size();
}
