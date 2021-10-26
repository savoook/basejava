package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Resume getResume(int position);

    protected abstract void updateResume(Resume resume, int position);

    protected abstract void saveResume(Resume resume, int position);

    protected abstract void deleteResume(String uuid, int position);

    protected abstract int findIndex(String uuid);

    public Resume get(String uuid) {
        int position = findIndex(uuid);
        if (position < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(position);
    }

    public void update(Resume resume) {
        int position = findIndex(resume.getUuid());
        if (position < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateResume(resume, position);
    }

    public final void save(Resume resume) {
        int position = findIndex(resume.getUuid());
        if (position >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(resume, position);
    }

    public void delete(String uuid) {
        int position = findIndex(uuid);
        if (position < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(uuid, position);
    }
}
