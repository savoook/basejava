package com.basejava.storage;

import com.basejava.Config;
import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.basejava.storage.TestData.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void getAllSorted() throws Exception {
        checkList(resume1, resume2, resume3);
        assertEquals(3, storage.size());
    }

    @Test
    void get() throws Exception {
        Resume actual = storage.get(resume1.getUuid());
        assertEquals(resume1, actual);
    }

    @Test()
    void getNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    void update() throws Exception {
        storage.update(resumeExist);
        checkList(resume1, resume2, resumeExist);
    }

    @Test
    void updateNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () -> storage.update(resumeNotExist));
    }

    @Test
    void save() throws Exception {
        storage.save(resumeNotExist);
        checkList(resume1, resume2, resume3, resumeNotExist);
        assertEquals(4, storage.size());
    }

    @Test
    void saveExist() throws Exception {
        assertThrows(ExistStorageException.class, () -> storage.save(resumeExist));
    }

    @Test
    void delete() throws Exception {
        storage.delete(resume1.getUuid());
        checkList(resume2, resume3);
        assertEquals(2, storage.size());
    }

    @Test
    void deleteNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () -> storage.delete(resumeNotExist.getUuid()));
    }

    private void checkList(Resume... resumes) {
        List<Resume> actual = storage.getAllSorted();
        List<Resume> expected = new ArrayList<>(Arrays.asList(resumes));
        assertEquals(expected, actual);
    }
}