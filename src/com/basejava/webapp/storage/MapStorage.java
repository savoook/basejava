
package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new LinkedHashMap<>();

    @Override
    protected Resume getResume(Object position) {
        return map.get((String) position);
    }

    @Override
    protected void updateResume(Resume resume, Object position) {
        map.put((String) position, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object position) {
        map.put((String) position, resume);
    }

    @Override
    protected void deleteResume(Object position) {
        map.remove((String) position);
    }

    @Override
    protected Object findPosition(String uuid) {
        return uuid;
    }

    @Override
    protected boolean elementExist(Object position) {
        return map.containsKey((String) position);
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

