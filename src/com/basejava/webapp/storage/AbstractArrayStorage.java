package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int idx = findIndex(uuid);
        if (idx < 0) {
            System.out.println("Резюме " + uuid + " не найдено");
            return null;
        }
        return storage[idx];
    }


    public void save(Resume resume) {
        int idx = findIndex(resume.getUuid());
        if (idx >= 0) {
            System.out.println("Резюме " + resume + " уже есть");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Массив переполнен");
        } else {
            System.out.println("Резюме " + resume + " новое, осуществляю вставку");
            saveToArray(resume, idx);
            size++;
        }
    }

    protected abstract void saveToArray(Resume resume, int positin);

    public void update(Resume resume) {
        int idx = findIndex(resume.getUuid());
        if (idx >= 0) {
            System.out.println("Резюме " + resume + " найдено, осуществляю update");
            storage[idx] = resume;
        } else {
            System.out.println("Резюме " + resume + " не найдено, невозможно его обновить");
        }
    }

    public void delete(String uuid) {
        int idx = findIndex(uuid);
        if (idx >= 0) {
            System.out.println("Резюме " + uuid + " найдено, удаляю");
            storage[idx] = null;
            if (size - idx + 1 >= 0)
                System.arraycopy(storage, idx + 1, storage, idx, size - idx + 1);
            storage[size] = null;
            size--;
        } else {
            System.out.println("Резюме c uuid = " + uuid + " не найдено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int findIndex(String uuid);
}
