package ru.vk.itmo.test.bazhenovkirill;

import ru.vk.itmo.Dao;
import ru.vk.itmo.Entry;
import ru.vk.itmo.bazhenovkirill.InMemoryDaoImpl;
import ru.vk.itmo.test.DaoFactory;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.charset.StandardCharsets;

@DaoFactory
public class DaoFactoryImpl implements DaoFactory.Factory<MemorySegment, Entry<MemorySegment>> {

    @Override
    public Dao<MemorySegment, Entry<MemorySegment>> createDao() {
        return new InMemoryDaoImpl();
    }

    @Override
    public String toString(MemorySegment memorySegment) {
        return memorySegment == null
                ? null
                : new String(memorySegment.toArray(ValueLayout.JAVA_BYTE), StandardCharsets.UTF_8);
    }

    @Override
    public MemorySegment fromString(String data) {
        return data == null ? null : MemorySegment.ofArray(data.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Entry<MemorySegment> fromBaseEntry(Entry<MemorySegment> baseEntry) {
        return baseEntry;
    }
}
