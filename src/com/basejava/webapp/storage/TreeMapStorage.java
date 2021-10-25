package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.HashMap;

public class TreeMapStorage extends MapStorage {
    public TreeMapStorage() {
        super(new HashMap<String, Resume>());
    }
}
