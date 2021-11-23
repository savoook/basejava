package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract Resume getResume(SK position);

    protected abstract void updateResume(Resume resume, SK position);

    protected abstract void saveResume(Resume resume, SK position);

    protected abstract void deleteResume(SK position);

    protected abstract SK findPosition(String uuid);

    protected abstract boolean isExist(SK position);

    protected abstract List<Resume> toList();

    public Resume get(String uuid) {
        LOG.info("get " + uuid);
        SK position = getExistResume(uuid);
        return getResume(position);
    }

    public void update(Resume resume) {
        LOG.info("update " + resume);
        SK position = getExistResume(resume.getUuid());
        updateResume(resume, position);
    }

    public final void save(Resume resume) {
        //LOG.info("save " + resume);
        SK position = getNotExistResume(resume.getUuid());
        saveResume(resume, position);
    }

    public void delete(String uuid) {
        LOG.info("delete " + uuid);
        SK position = getExistResume(uuid);
        deleteResume(position);
    }

    private SK getNotExistResume(String uuid) {
        SK position = findPosition(uuid);
        if (isExist(position)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return position;
    }

    private SK getExistResume(String uuid) {
        SK position = findPosition(uuid);
        if (!isExist(position)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return position;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("get all sorted");
        List<Resume> resumes = toList();
        Collections.sort(resumes);
        return resumes;
    }
}
