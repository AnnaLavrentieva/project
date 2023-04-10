package com.lavrentieva.serviceDto;

import com.lavrentieva.dto.ItemDto;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Component
public class ItemDTOService {
    private static final List<ItemDto> CACHE = new LinkedList<>();


    public void addToCache(final ItemDto item) {
        final int index = getIndexFromCache(item.getNumber());
        if (index < 0) {
            CACHE.add(item);
        } else {
            CACHE.set(index, item);
        }
    }

    private int getIndexFromCache(final int number) {
        OptionalInt index = IntStream.range(0, CACHE.size())
                .filter(i -> CACHE.get(i).getNumber() == number)
                .findFirst();
        return index.orElse(-1);
    }

    public List<ItemDto> getAllFromCache() {
        return CACHE;
    }

    public double getTotalSumFromCache() {
        return CACHE.stream()
                .mapToDouble(itemDto -> itemDto.getAmount() * itemDto.getPrice())
                .sum();
    }

    public ItemDto getFromCache(final int number) {
        return CACHE.stream()
                .filter(itemDto -> itemDto.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Item with number" + number + "not found"));
    }

    public void deleteFromCache(final int number) {
        CACHE.removeIf(itemDto -> itemDto != null && itemDto.getNumber() == number);
    }

    public void clearCache() {
        CACHE.clear();
    }

}
