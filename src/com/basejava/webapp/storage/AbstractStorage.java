package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    Object entity = new Object();

    protected abstract Resume abstractGet(int position);

    protected abstract void abstractUpdate(Resume resume, int position);

    protected abstract void abstractSave(Resume resume, int position);

    protected abstract void abstractDelete(String uuid, int position);

    /*    protected abstract void saveElement(Resume resume, int position);*/

    protected abstract int abstactFind(String uuid);


    public Resume get(String uuid) {
        int position = abstactFind(uuid);
        if (position<0){
            throw new NotExistStorageException(uuid);
        }
        return abstractGet(position);
    }

    public void update(Resume resume) {
        int position = abstactFind(resume.getUuid());
        if (position<0){
            throw new NotExistStorageException(resume.getUuid());
        }
        abstractUpdate(resume, position);
    }

    public final void save(Resume resume) {
        int position = abstactFind(resume.getUuid());
        if (position>=0){
            throw new ExistStorageException(resume.getUuid());
        }
        abstractSave(resume, position);
    }

    public void delete(String uuid) {
        int position = abstactFind(uuid);
        if (position<0){
            throw new NotExistStorageException(uuid);
        }
        abstractDelete(uuid, position);
    }


}
