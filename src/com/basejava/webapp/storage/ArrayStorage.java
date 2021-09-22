package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private static int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (findResume(resume.getUuid()) == null) {
            System.out.println("Резюме " + resume + " новое, осуществляю вставку");
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Резюме" + resume + "уже есть");
        }
    }
       /* boolean exist = false;
        for (int i = 0; i < size; i++) {
            if (r.getUuid().equals(storage[i].getUuid())) {
                System.out.println("Резюме" + r + "уже есть");
                exist = true;
            }
        }
        if (!exist) {
            System.out.println("Резюме " + r + " новое, осуществляю вставку");
            storage[size] = r;
            size++;
        }
    }*/

    public void update(Resume resume) {
        Resume r = findResume(resume.getUuid());
        if (r != null) {
            System.out.println("Резюме " + resume + " найдено, осуществляю update");
            r = resume;
        } else {
            System.out.println("Резюме " + resume + " не найдено, невозможно его обновить");
        }
    }
/*        boolean exist = false;
        for (int i = 0; i < size; i++) {
            if (resume.getUuid().equals(storage[i].getUuid())) {
                System.out.println("Резюме " + resume + " найдено, осуществляю update");
                exist = true;
                storage[i] = resume;
            }
        }
        if (!exist) {
            System.out.println("Резюме " + resume + " не найдено, не возможно его обновить");
        }
    }*/

    public Resume get(String uuid) {
        Resume resume = findResume(uuid);
        if (findResume(uuid) != null) {
            System.out.println("Резюме " + uuid + " найдено");
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
        }
        return resume;
    }
/*        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                System.out.println("Резюме " + storage[i] + " найдено");
                return storage[i];
            }
        }
        System.out.println("Резюме c uuid = " + uuid + " не найдено");
        return null;*/

    public void delete(String uuid) {
        /*        Resume resume = find(uuid);*/
        int idx = findIdx(uuid);
        if (idx != -1) {
            System.out.println("Резюме " + uuid + " найдено, удаляю");
            storage[idx] = null;
            for (int i = idx + 1; i < size; i++) {
                storage[i - 1] = storage[i];
            }
            storage[size] = null;
            size--;
        } else {
            System.out.println("Резюме c uuid = " + uuid + " не найдено");
        }
    }
/*        boolean exist = false;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                System.out.println("Резюме " + storage[i] + " найдено, удаляю");
                exist = true;
                storage[i] = null;
                for (i = i + 1; i < size; i++) {
                    storage[i - 1] = storage[i];
                }
                storage[size] = null;
                size--;
                break;
            }
        }
        if (!exist) {
            System.out.println("Резюме c uuid = " + uuid + " не найдено");
        }*/

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] storageNew = Arrays.copyOf(storage, size);
        return storageNew;
    }

    public int size() {
        return size;
    }

    private Resume findResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        return null;
    }

    private int findIdx(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}


