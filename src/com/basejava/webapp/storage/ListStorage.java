package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private static final List<Resume> listStorage = new ArrayList<>();

    @Override
    protected Resume getResume(int position) {
        return listStorage.get(position);
    }

    @Override
    protected void updateResume(Resume resume, int position) {
        listStorage.set(position, resume);
    }

    @Override
    protected void saveResume(Resume resume, int position) {
        listStorage.add(resume);
    }

    @Override
    protected void deleteResume(String uuid, int position) {
        listStorage.remove(position);
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
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