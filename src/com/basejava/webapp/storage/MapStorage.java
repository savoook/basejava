
package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Map;

public abstract class MapStorage extends AbstractStorage {
    protected Map<String, Resume> map;

    public MapStorage(Map<String, Resume> map) {
        this.map = map;
    }

    @Override
    protected Resume getResume(int position) {
        return /*map.get(Object)*/null;
    }

    @Override
    protected void updateResume(Resume resume, int position) {
        /* map.put(Object, resume);*/
    }

    @Override
    protected void saveResume(Resume resume, int position) {
        /* map.merge(Object, resume);*/
    }

    @Override
    protected void deleteResume(String uuid, int position) {
        map.remove(uuid);
    }

    @Override
    protected int findIndex(String uuid) {
        return /*map.get(uuid)*/0;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }

    @Override
    public int size() {
        return map.size();
    }
}

