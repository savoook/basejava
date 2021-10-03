package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int idx = Arrays.binarySearch(storage, 0, size, resume);
        //int idx = Arrays.binarySearch(storage, resume, Resume::compareTo);
        if (idx /*!=*/ < 0) {
            int pos =/* idx < 0 ? */-idx - 1 /*: idx;*/;
            System.arraycopy(storage, pos, storage, pos + 1, size - pos);
            storage[pos] = resume;
            size++;
        } /*else {
            System.arraycopy(storage, 0, storage, 1, size - 1);
            storage[0] = resume;
        }*/ else {
            System.out.println("Резюме с uuid" + resume.getUuid() + "уже существует");
        }
    }

    @Override
    protected int getIdx(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
