import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (Resume a : storage) {
            if (a == null) {
                storage[Arrays.asList(storage).indexOf(a)] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) {
                if (uuid.equals(storage[i].getUuid())) {
                    return storage[i];
                }
            }
        }
        return null;
    }


    void delete(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                int k = Arrays.asList(storage).indexOf(r);
                storage[Arrays.asList(storage).indexOf(r)] = null;

                for (int i = k + 1; i < storage.length - 1; i++) {
                    storage[i - 1] = storage[i];
                    storage[i] = null;
                }
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int count = 0;
        for (Resume a : storage) {
            if (a != null) {
                count += 1;
            } else {
                break;
            }
        }
        Resume[] storageNew = new Resume[count];
        for (int i = 0; i < count; i++) {
            if (storage[i] != null) {
                storageNew[i] = storage[i];
            } else {
                break;
            }
        }
        return storageNew;
    }

    int size() {
        return storage.length;
    }
}


