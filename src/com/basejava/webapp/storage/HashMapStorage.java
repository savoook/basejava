package com.basejava.webapp.storage;

import java.util.HashMap;

public class HashMapStorage extends MapStorage {
    public HashMapStorage() {
        super(new HashMap<>());
    }
}
