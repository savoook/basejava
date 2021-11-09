package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

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
    protected Resume getResume(Object position) {
        return storage[(int) position];
    }

    @Override
    protected void updateResume(Resume resume, Object position) {
        storage[(int) position] = resume;
    }

    @Override
    protected void saveResume(Resume resume, Object position) {
        if (size == STORAGE_LIMIT)
            throw new StorageException("Storage overflow", resume.getUuid());
        insert(resume, (Integer) position);
        size++;
    }

    @Override
    protected void deleteResume(Object position) {
        int idx = (int) position;
        storage[idx] = null;
        if (size - idx + 1 >= 0)
            System.arraycopy(storage, idx + 1, storage, idx, size - idx + 1);
        storage[size] = null;
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void insert(Resume resume, int idx);

    @Override
    protected boolean isExist(Object position) {
        return (int) position >= 0;
    }

    @Override
    protected List<Resume> toList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }
}
