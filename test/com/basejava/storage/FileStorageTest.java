package com.basejava.storage;

import com.basejava.storage.serializer.ObjectStreamSerializer;

class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}