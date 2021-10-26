package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected Resume getResume(int position) {
        return storage[position];
    }

    @Override
    protected void updateResume(Resume resume, int position) {
        storage[position] = resume;
    }

    @Override
    protected void saveResume(Resume resume, int position) {
        if (size == STORAGE_LIMIT)
            throw new StorageException("Storage overflow", resume.getUuid());
        insert(resume, position);
        size++;
    }

    @Override
    protected void deleteResume(String uuid, int position) {
        storage[position] = null;
        if (size - position + 1 >= 0)
            System.arraycopy(storage, position + 1, storage, position, size - position + 1);
        storage[size] = null;
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void insert(Resume resume, int idx);
}
