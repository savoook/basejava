package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private static final List<Resume> listStorage = new ArrayList<>();

    @Override
    protected Resume getResume(Object position) {
        return listStorage.get((int) position);
    }

    @Override
    protected void updateResume(Resume resume, Object position) {
        listStorage.set((int) position, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object position) {
        listStorage.add(resume);
    }

    @Override
    protected void deleteResume(Object position) {
        listStorage.remove((int) position);
    }

    @Override
    protected Object findIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean elementExist(Object position) {
        return listStorage.size() > (int) position && (int) position >= 0;
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