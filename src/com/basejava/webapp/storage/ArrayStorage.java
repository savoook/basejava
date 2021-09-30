package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

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

    public void update(Resume resume) {
        int idx = getIdx(resume.getUuid());
        if (idx != -1) {
            System.out.println("Резюме " + resume + " найдено, осуществляю update");
            storage[idx] = resume;
        } else {
            System.out.println("Резюме " + resume + " не найдено, невозможно его обновить");
        }
    }

    public Resume get(String uuid) {
        int idx = getIdx(uuid);
        if (idx != -1) {
            System.out.println("Резюме " + uuid + " найдено");
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
            return null;
        }
        return storage[idx];
    }

    public void delete(String uuid) {
        int idx = getIdx(uuid);
        if (idx != -1) {
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

    protected int getIdx(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}


