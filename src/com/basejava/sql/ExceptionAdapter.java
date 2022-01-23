package com.basejava.sql;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.StorageException;

import java.sql.SQLException;

public class ExceptionAdapter {
    public ExceptionAdapter() {
    }

    public static StorageException transfer(SQLException e) {
        if (e.getSQLState().equals("23505")) {
            return new ExistStorageException("");
        }
        return new StorageException(e);
    }
}
