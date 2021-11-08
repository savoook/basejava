package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.List;

public interface Storage {
    void clear();

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
/*    Resume[] getAll();*/

    List<Resume> getAllSorted();

    int size();
}
