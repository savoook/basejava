package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Resume getResume(Object position);

    protected abstract void updateResume(Resume resume, Object position);

    protected abstract void saveResume(Resume resume, Object position);

    protected abstract void deleteResume(Object position);

    protected abstract Object findPosition(String uuid);

    protected abstract boolean isExist(Object position);

    protected abstract List<Resume> toList();

    public Resume get(String uuid) {
        Object position = getExistResume(uuid);
        return getResume(position);
    }

    public void update(Resume resume) {
        Object position = getExistResume(resume.getUuid());
        updateResume(resume, position);
    }

    public final void save(Resume resume) {
        Object position = getNotExistResume(resume.getUuid());
        saveResume(resume, position);
    }

    public void delete(String uuid) {
        Object position = getExistResume(uuid);
        deleteResume(position);
    }

    private Object getNotExistResume(String uuid) {
        Object position = findPosition(uuid);
        if (isExist(position))
            throw new ExistStorageException(uuid);
        return position;
    }

    private Object getExistResume(String uuid) {
        Object position = findPosition(uuid);
        if (!isExist(position))
            throw new NotExistStorageException(uuid);
        return position;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = toList();
        Collections.sort(resumes);
        return resumes;
    }
}
