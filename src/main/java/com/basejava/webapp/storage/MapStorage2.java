
package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapStorage2 extends AbstractStorage {
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
    protected boolean elementExist(Object position) {
        return (Resume) position != null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return map.values().stream().sorted(Comparator.comparing(Resume::getFullName)).collect(Collectors.toList());
    }

/*    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }*/

    @Override
    public int size() {
        return map.size();
    }
}

