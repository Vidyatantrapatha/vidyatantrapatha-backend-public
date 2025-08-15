package me.electronicsboy.vidyatantrapatha.temporary.repositories;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import me.electronicsboy.vidyatantrapatha.temporary.objects.TemporaryDeviceObject;

@Repository
public class TemporaryDeviceObjectRepository {
    private final Map<Long, TemporaryDeviceObject> storeById = new ConcurrentHashMap<>();
    private final Map<String, TemporaryDeviceObject> storeByDeviceId = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<TemporaryDeviceObject> findAll() {
        return new ArrayList<>(storeById.values());
    }

    public Optional<TemporaryDeviceObject> findById(Long id) {
        return Optional.ofNullable(storeById.get(id));
    }

    public Optional<TemporaryDeviceObject> findByDeviceId(String deviceId) {
        return Optional.ofNullable(storeByDeviceId.get(deviceId));
    }

    public TemporaryDeviceObject save(TemporaryDeviceObject item) {
        if (item.getId() == null) {
            item.setId(idCounter.getAndIncrement());
        }
        storeById.put(item.getId(), item);

        if (item.getDeviceId() != null) {
        	storeByDeviceId.put(item.getDeviceId(), item);
        }
        return item;
    }

    public void deleteById(Long id) {
        TemporaryDeviceObject removed = storeById.remove(id);
        if (removed != null && removed.getDeviceId() != null) {
        	storeByDeviceId.remove(removed.getDeviceId());
        }
    }
    
    public void deleteById(TemporaryDeviceObject object) {
        deleteById(object.getId());
    }

    public void clear() {
        storeById.clear();
        storeByDeviceId.clear();
    }
}

