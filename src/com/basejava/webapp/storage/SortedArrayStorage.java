package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void inject(Resume resume, int index) {
        System.arraycopy(storage, -index - 1, storage, -index, size - (-index - 1));
        storage[-index - 1] = resume;
    }

    @Override
    protected int getIdx(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
