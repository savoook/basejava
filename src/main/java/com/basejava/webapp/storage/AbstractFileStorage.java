package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writeable");
        }
        this.directory = directory;
    }

    @Override
    protected Resume getResume(File file) {
        Resume resume;// = new Resume(file.getName(), "");
        try {
            resume = doGet(file);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
        return resume;
    }

    protected abstract Resume doGet(File file) throws IOException;

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Error", resume.getUuid(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Error", resume.getUuid(), e);
        }
    }

    abstract void doWrite(Resume resume, File file) throws IOException;

    @Override
    protected void deleteResume(File file) {
        String[] files = directory.list();
        for (String s : Objects.requireNonNull(files)) {
            if (s.equals(file.getName())) {
                try {
                    doDelete(file);
                } catch (IOException e) {
                    throw new StorageException("IO Error", file.getName(), e);
                }
            }
        }
    }

    protected abstract void doDelete(File file) throws IOException;

    @Override
    protected File findPosition(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> toList() {
        if (directory.length() != 0) {
            List<Resume> resumes = new ArrayList<>();
            for (File f : Objects.requireNonNull(directory.listFiles())) {
                Resume resume;// = null;
                try {
                    resume = doGet(f);
                } catch (IOException e) {
                    throw new StorageException("IO Error", f.getName(), e);
                }
                resumes.add(resume);
            }
            return resumes;
        }
        return null;
    }

    @Override
    public void clear() {
        if (directory.length() != 0) {
            for (File f : Objects.requireNonNull(directory.listFiles())) {
                if (f.isFile()) {
                    f.delete();
                }
            }
        }
    }

    @Override
    public int size() {
        String[] files = directory.list();
        return files != null ? files.length : 0;
    }
}
