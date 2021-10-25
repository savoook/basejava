package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Iterator;
import java.util.List;

public abstract class ListStorage extends AbstractStorage {
    protected List<Resume> listStorage;

    public ListStorage(List<Resume> listStorage) {
        this.listStorage = listStorage;
    }

    @Override
    protected Resume abstractGet(int position) {
        return listStorage.get(position);
    }

    @Override
    protected void abstractUpdate(Resume resume, int position) {
        listStorage.set(position, resume);
    }

    @Override
    protected void abstractSave(Resume resume, int position) {
        listStorage.add(resume);
    }

    @Override
    protected void abstractDelete(String uuid, int position) {
        listStorage.remove(position);
    }

    @Override
    protected int abstactFind(String uuid) {
        Iterator<Resume> it = listStorage.iterator();
        while (it.hasNext()) {
            Resume r = it.next();
            if (r.getUuid().equals(uuid)) {
                return listStorage.indexOf(r);
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        return listStorage.toArray(new Resume[listStorage.size()]);
    }

    @Override
    public int size() {
        return listStorage.size();
    }
}