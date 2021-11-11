package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    protected abstract Resume getResume(SK position);

    protected abstract void updateResume(Resume resume, SK position);

    protected abstract void saveResume(Resume resume, SK position);

    protected abstract void deleteResume(SK position);

    protected abstract SK findPosition(String uuid);

    protected abstract boolean isExist(SK position);

    protected abstract List<Resume> toList();

    public Resume get(String uuid) {
        SK position = getExistResume(uuid);
        return getResume(position);
    }

    public void update(Resume resume) {
        SK position = getExistResume(resume.getUuid());
        updateResume(resume, position);
    }

    public final void save(Resume resume) {
        SK position = getNotExistResume(resume.getUuid());
        saveResume(resume, position);
    }

    public void delete(String uuid) {
        SK position = getExistResume(uuid);
        deleteResume(position);
    }

    private SK getNotExistResume(String uuid) {
        SK position = findPosition(uuid);
        if (isExist(position))
            throw new ExistStorageException(uuid);
        return position;
    }

    private SK getExistResume(String uuid) {
        SK position = findPosition(uuid);
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
