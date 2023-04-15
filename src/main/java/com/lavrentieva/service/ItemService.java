package com.lavrentieva.service;

import com.lavrentieva.dto.ItemDtoMovement;
import com.lavrentieva.mapper.ItemDtoMovementMapper;
import com.lavrentieva.model.Item;
import com.lavrentieva.model.Person;
import com.lavrentieva.model.Warehouse;
import com.lavrentieva.repository.ItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAll() {
        Iterable<Item> items = itemRepository.findAll();
        return StreamSupport.stream(items.spliterator(), false).collect(Collectors.toList());
    }

    public Optional<Item> getById(final String id) {
        return itemRepository.findById(id);
    }

    public String save(final Item item) {
        return itemRepository.save(item).getId();
    }

    public void saveAllFromList(final List<Item> itemList) {
        itemRepository.saveAll(itemList);
    }

    public Page<Item> getPageByCondition(int page, int size, Warehouse warehouse, Person person) {
        final List<Order> ordersForSorting = setUpSortList();
        final Pageable pageable = PageRequest.of(page - 1, size, Sort.by(ordersForSorting).ascending());
        final Page<Item> itemsPage = findPageByDifferentConditions(warehouse,person,pageable);
        return itemsPage;
    }

    private Page<Item> findPageByDifferentConditions(Warehouse warehouse, Person person, Pageable pageable) {
        Page<Item> itemsPage = null;
        switch (Objects.nonNull(warehouse) + "|" + Objects.nonNull(person)) {
            case "true|true":
                itemsPage = itemRepository.findByWarehouseAndPerson(warehouse, person, pageable);
                break;
            case "true|false":
                itemsPage = itemRepository.findByWarehouse(warehouse, pageable);
                break;
            case "false|true":
                itemsPage = itemRepository.findByPerson(person, pageable);
                break;
            case "false|false":
                itemsPage = itemRepository.findAll(pageable);
                break;
        }
        return itemsPage;
    }

    private List<Order> setUpSortList() {
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.ASC, "deploymentDate"));
        orders.add(new Order(Sort.Direction.DESC, "wareGroup"));
        return orders;
    }

//    public void updatePrice(final String id, final int price) {
//        itemRepository.findById(id)
//                .ifPresent(item -> {
//                    item.setPrice(price);
//                    itemRepository.save(item);
//                });
//    }

//    public String delete(final String id) {
//        itemRepository.deleteById(id);
//        return id;
//    }
}
