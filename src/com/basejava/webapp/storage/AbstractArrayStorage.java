package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public Resume get(String uuid) {
        int idx = findIndex(uuid);
        if (idx < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[idx];
    }

    public void update(Resume resume) {
        int idx = findIndex(resume.getUuid());
        if (idx < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[idx] = resume;
        }
    }

    public void save(Resume resume) {
        int idx = findIndex(resume.getUuid());
        if (idx >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            saveToArray(resume, idx);
            size++;
        }
    }

    public void delete(String uuid) {
        int idx = findIndex(uuid);
        if (idx < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            storage[idx] = null;
            if (size - idx + 1 >= 0)
                System.arraycopy(storage, idx + 1, storage, idx, size - idx + 1);
            storage[size] = null;
            size--;
        }
    }

    protected abstract void saveToArray(Resume resume, int position);

    protected abstract int findIndex(String uuid);
}
