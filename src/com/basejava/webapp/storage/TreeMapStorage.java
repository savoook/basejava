package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.HashMap;

public class HashMapStorage extends MapStorage {
    public HashMapStorage() {
        super(new HashMap<String, Resume>());
    }
}
