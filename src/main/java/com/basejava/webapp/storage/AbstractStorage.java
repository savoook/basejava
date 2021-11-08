package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Resume getResume(Object position);

    protected abstract void updateResume(Resume resume, Object position);

    protected abstract void saveResume(Resume resume, Object position);

    protected abstract void deleteResume(Object position);

    protected abstract Object findPosition(String uuid);

    protected abstract boolean elementExist(Object position);

    public Resume get(String uuid) {
        Object position = getExistElement(uuid);
        return getResume(position);
    }

    public void update(Resume resume) {
        Object position = getExistElement(resume.getUuid());
        updateResume(resume, position);
    }

    public final void save(Resume resume) {
        Object position = getNotExistElement(resume.getUuid());
        saveResume(resume, position);
    }

    public void delete(String uuid) {
        Object position = getExistElement(uuid);
        deleteResume(position);
    }

    private Object getNotExistElement(String uuid) {
        Object position = findPosition(uuid);
        if (elementExist(position))
            throw new ExistStorageException(uuid);
        return position;
    }

    private Object getExistElement(String uuid) {
        Object position = findPosition(uuid);
        if (!elementExist(position))
            throw new NotExistStorageException(uuid);
        return position;
    }
}
