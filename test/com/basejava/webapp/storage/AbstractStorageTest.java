package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractStorageTest {

    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final String FULL_NAME_1 = "Person1";
    private static final String FULL_NAME_2 = "Person2";
    private static final String FULL_NAME_3 = "Person3";
    private static final String FULL_NAME_4 = "Person4";

    private final Resume resume1 = new Resume(UUID_1, FULL_NAME_1);
    private final Resume resume2 = new Resume(UUID_2, FULL_NAME_2);
    private final Resume resume3 = new Resume(UUID_3, FULL_NAME_3);
    protected final Resume resumeExist = new Resume(UUID_3, FULL_NAME_3);
    protected final Resume resumeNotExist = new Resume(UUID_4, FULL_NAME_4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    {
        resume1.fillContact(ContactType.PHONE, "987");
        ArrayList<Period> periods = new ArrayList<>();
        Period period = new Period(LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1), "Курс \"Объектно-ориентированный анализ ИС." +
                " Концептуальное моделирование на UML.\"", null);
        periods.add(period);
        Organization org = new Organization("Школа", "www", periods);
        ArrayList<Organization> organizations = new ArrayList<>();
        resume1.fillSection(SectionType.EDUCATION, new ExperienceSection(organizations));
    }

    @BeforeEach
    public void SetUp() throws Exception {
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

    @Test
    private void checkList(Resume... resumes) {
        List<Resume> actual = storage.getAllSorted();
        List<Resume> expected = new ArrayList<>(Arrays.asList(resumes));
        assertEquals(expected, actual);
    }
}