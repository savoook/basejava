
package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> map = new LinkedHashMap<>();

    @Override
    protected Resume getResume(Object position) {
        return (Resume) position;
    }

    @Override
    protected void updateResume(Resume resume, Object position) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveResume(Resume resume, Object position) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object position) {
        map.remove(((Resume) position).getUuid());
    }

    @Override
    protected Object findPosition(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Object position) {
        return (Resume) position != null;
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

