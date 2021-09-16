import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    static int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            Arrays.fill(storage, 0, i, null);
            size = 0;
        }
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                storage[i] = null;
                for (i = i + 1; i < size; i++) {
                    storage[i - 1] = storage[i];
                    storage[i] = null;
                }
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] storageNew = Arrays.copyOf(storage, size);
        return storageNew;
    }

    int size() {
        return size;
    }
}


