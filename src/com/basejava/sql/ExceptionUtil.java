package com.basejava.sql;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ExceptionUtil {
    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException("");
            }
        }
        return new StorageException(e);
    }
}
