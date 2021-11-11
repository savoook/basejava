
package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> map = new LinkedHashMap<>();

    @Override
    protected Resume getResume(Resume position) {
        return position;
    }

    @Override
    protected void updateResume(Resume resume, Resume position) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveResume(Resume resume, Resume position) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Resume position) {
        map.remove((position).getUuid());
    }

    @Override
    protected Resume findPosition(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Resume position) {
        return position != null;
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

