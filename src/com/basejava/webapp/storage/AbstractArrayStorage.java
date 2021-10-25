package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage  {
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
    protected Resume abstractGet(int position) {
//        int idx = abstactFind(uuid);
//        if (idx < 0)
//            throw new NotExistStorageException(uuid);
        return storage[position];
    }

    @Override
    protected void abstractUpdate(Resume resume, int position) {
/*        int idx = abstactFind(resume.getUuid());
        if (idx < 0)
            throw new NotExistStorageException(resume.getUuid());*/
        storage[position] = resume;
    }

    @Override
    protected void abstractSave(Resume resume, int position) {
       /* int idx = abstactFind(resume.getUuid());
        */if (size == STORAGE_LIMIT)
            throw new StorageException("Storage overflow", resume.getUuid());
        /*if (idx >= 0)
            throw new ExistStorageException(resume.getUuid());*/
        insert(resume, position);
        size++;
    }




    @Override
    protected void abstractDelete(String uuid, int position) {
       /* int idx = abstactFind(uuid);
        if (idx < 0)
            throw new NotExistStorageException(uuid);*/
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

    /*public int size() {
        return size;
    }

    public void abstractCle() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    *//**
     * @return array, contains only Resumes in storage (without null)
     *//*

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public Resume ab(String uuid) {
        int idx = findIndex(uuid);
        if (idx < 0)
            throw new NotExistStorageException(uuid);
        return storage[idx];
    }

    public void update(Resume resume) {
        int idx = findIndex(resume.getUuid());
        if (idx < 0)
            throw new NotExistStorageException(resume.getUuid());
        storage[idx] = resume;
    }

    public void save(Resume resume) {
        int idx = findIndex(resume.getUuid());
        if (size == STORAGE_LIMIT)
            throw new StorageException("Storage overflow", resume.getUuid());
        if (idx >= 0)
            throw new ExistStorageException(resume.getUuid());
        saveToArray(resume, idx);
        size++;
    }

    public void delete(String uuid) {
        int idx = findIndex(uuid);
        if (idx < 0)
            throw new NotExistStorageException(uuid);
        storage[idx] = null;
        if (size - idx + 1 >= 0)
            System.arraycopy(storage, idx + 1, storage, idx, size - idx + 1);
        storage[size] = null;
        size--;
    }
*/

}
