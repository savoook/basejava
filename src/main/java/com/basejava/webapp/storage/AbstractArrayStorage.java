package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        if (size == STORAGE_LIMIT)
            throw new StorageException("Storage overflow", resume.getUuid());
        insertElement(resume, searchKey);
        size++;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        int idx = searchKey;
        storage[idx] = null;
        if (size - idx + 1 >= 0)
            System.arraycopy(storage, idx + 1, storage, idx, size - idx + 1);
        storage[size] = null;
        size--;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    protected abstract void insertElement(Resume resume, int idx);
}
