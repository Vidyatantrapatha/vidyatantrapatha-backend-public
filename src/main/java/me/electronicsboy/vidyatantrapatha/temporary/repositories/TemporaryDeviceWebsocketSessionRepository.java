package me.electronicsboy.vidyatantrapatha.temporary.repositories;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import me.electronicsboy.vidyatantrapatha.temporary.objects.TemporaryDeviceWebsocketSession;

@Repository
public class TemporaryDeviceWebsocketSessionRepository {
    private final Map<Long, TemporaryDeviceWebsocketSession> storeById = new ConcurrentHashMap<>();
    private final Map<String, TemporaryDeviceWebsocketSession> storeBySessionId = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<TemporaryDeviceWebsocketSession> findAll() {
        return new ArrayList<>(storeById.values());
    }

    public Optional<TemporaryDeviceWebsocketSession> findById(Long id) {
        return Optional.ofNullable(storeById.get(id));
    }

    public Optional<TemporaryDeviceWebsocketSession> findBySessionId(String sessionId) {
        return Optional.ofNullable(storeBySessionId.get(sessionId));
    }

    public TemporaryDeviceWebsocketSession save(TemporaryDeviceWebsocketSession item) {
        if (item.getId() == null) {
            item.setId(idCounter.getAndIncrement());
        }
        storeById.put(item.getId(), item);

        if (item.getSessionId() != null) {
            storeBySessionId.put(item.getSessionId(), item);
        }
        return item;
    }

    public void deleteById(Long id) {
        TemporaryDeviceWebsocketSession removed = storeById.remove(id);
        if (removed != null && removed.getSessionId() != null) {
            storeBySessionId.remove(removed.getSessionId());
        }
    }
    
    public void deleteBySessionId(String sessionId) {
    	storeBySessionId.remove(sessionId);
    	TemporaryDeviceWebsocketSession session = findBySessionId(sessionId).orElseThrow();
    	storeById.remove(session.getId());
    }

    public void clear() {
        storeById.clear();
        storeBySessionId.clear();
    }
}

