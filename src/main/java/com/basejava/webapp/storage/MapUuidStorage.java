
package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
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
    protected boolean isExist(Object position) {
        return map.containsKey((String) position);
    }

    @Override
    protected List<Resume> toList() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }
}

