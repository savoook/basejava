package com.basejava.storage;

import com.basejava.storage.serializer.ObjectStreamSerializer;

class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}