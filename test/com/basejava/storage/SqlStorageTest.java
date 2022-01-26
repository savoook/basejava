package com.basejava.storage;

import com.basejava.Config;

class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}