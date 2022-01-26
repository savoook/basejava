package com.basejava.storage;

import com.basejava.Config;
import com.basejava.ResumeTestData;
import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    private static final String FULL_NAME_1 = "Person1";
    private static final String FULL_NAME_2 = "Person2";
    private static final String FULL_NAME_3 = "Person3";
    private static final String FULL_NAME_4 = "Person4";

    private final Resume resume1 = ResumeTestData.createResume(UUID_1, FULL_NAME_1);
    private final Resume resume2 = ResumeTestData.createResume(UUID_2, FULL_NAME_2);
    private final Resume resume3 = ResumeTestData.createResume(UUID_3, FULL_NAME_3);
    protected final Resume resumeExist = ResumeTestData.createResume(UUID_3, FULL_NAME_3);
    protected final Resume resumeNotExist = ResumeTestData.createResume(UUID_4, FULL_NAME_4);

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
        Resume actual = storage.get(UUID_2);
        assertEquals(resume2, actual);
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
        storage.delete(UUID_1);
        checkList(resume2, resume3);
        assertEquals(2, storage.size());
    }

    @Test
    void deleteNotExist() throws Exception {
        assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_4));
    }

    private void checkList(Resume... resumes) {
        List<Resume> actual = storage.getAllSorted();
        List<Resume> expected = new ArrayList<>(Arrays.asList(resumes));
        assertEquals(expected, actual);
    }
}