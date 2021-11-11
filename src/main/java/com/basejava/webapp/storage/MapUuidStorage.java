
package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
    private Map<String, Resume> map = new LinkedHashMap<>();

    @Override
    protected Resume getResume(String position) {
        return map.get(position);
    }

    @Override
    protected void updateResume(Resume resume, String position) {
        map.put(position, resume);
    }

    @Override
    protected void saveResume(Resume resume, String position) {
        map.put(position, resume);
    }

    @Override
    protected void deleteResume(String position) {
        map.remove(position);
    }

    @Override
    protected String findPosition(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String position) {
        return map.containsKey(position);
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

