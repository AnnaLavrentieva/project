package com.lavrentieva.service;

import com.lavrentieva.model.Item;
import com.lavrentieva.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    private static final Set<Item> CACHE = new HashSet<>();

    @Autowired
    public ItemService(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Iterable<Item> getAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> getById(final String id) {
        return itemRepository.findById(id);
    }

    public String save(final Item item) {
        return itemRepository.save(item).getId();
    }

    public void addToCache(final Item item) {
        CACHE.add(item);
    }

    public Set<Item> getAllFromCache() {
        return CACHE;
    }

    public double getTotalPriceFromCache() {
        return CACHE.stream()
                .mapToDouble(Item::getPrice).sum();
    }

    public void saveAllFromCacheToDatabase() {
        itemRepository.saveAll(CACHE);
    }

    public void clearCache() {
        CACHE.clear();
    }

    public void updatePrice(final String id, final int price) {
        itemRepository.findById(id)
                .ifPresent(item -> {
                    item.setPrice(price);
                    itemRepository.save(item);
                });
    }

    public String delete(final String id) {
        itemRepository.deleteById(id);
        return id;
    }
}
