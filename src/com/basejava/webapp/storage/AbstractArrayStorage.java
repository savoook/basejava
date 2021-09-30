package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public Resume get(String uuid) {
        int idx = getIdx(uuid);
        if (idx == -1) {
            System.out.println("Резюме " + uuid + " не найдено");
            return null;
        }
        return storage[idx];
    }

    protected abstract int getIdx(String uuid);
}
