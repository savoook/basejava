package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int idx = getIdx(resume.getUuid());
        if (idx != -1) {
            System.out.println("Резюме " + resume + " уже есть");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Массив переполнен");
        } else {
            System.out.println("Резюме " + resume + " новое, осуществляю вставку");
            storage[size] = resume;
            size++;
        }
    }

    @Override
    protected int getIdx(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}


