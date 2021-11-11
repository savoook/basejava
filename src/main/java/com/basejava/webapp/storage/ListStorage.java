package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private List<Resume> listStorage = new ArrayList<>();

    @Override
    protected Resume getResume(Integer position) {
        return listStorage.get(position);
    }

    @Override
    protected void updateResume(Resume resume, Integer position) {
        listStorage.set(position, resume);
    }

    @Override
    protected void saveResume(Resume resume, Integer position) {
        listStorage.add(resume);
    }

    @Override
    protected void deleteResume(Integer position) {
        listStorage.remove(position.intValue());
    }

    @Override
    protected Integer findPosition(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Integer position) {
        return position >= 0;
    }

    @Override
    protected List<Resume> toList() {
        return new ArrayList<>(listStorage);
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public int size() {
        return listStorage.size();
    }
}