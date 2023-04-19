package com.lavrentieva.service;

import com.lavrentieva.dto.ItemDtoMovement;
import com.lavrentieva.dto.ItemsDtoInListMovement;
import com.lavrentieva.model.*;
import com.lavrentieva.repository.ItemRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final WareMovementRecordService wareMovementRecordService;

    @Autowired
    public ItemService(ItemRepository itemRepository, WareMovementRecordService wareMovementRecordService) {
        this.itemRepository = itemRepository;
        this.wareMovementRecordService = wareMovementRecordService;
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

    public Page<Item> getPageByCondition(@NonNull int page, @NonNull int size,
                                         Warehouse warehouse, Person person) {
        final List<Order> ordersForSorting = setUpSortList();
        final Pageable pageable = PageRequest.of(page - 1, size, Sort.by(ordersForSorting).ascending());
        final Page<Item> itemsPage = findPageByDifferentConditions(warehouse, person, pageable);
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

    public ItemsDtoInListMovement getListForMovement(@NonNull ItemsDtoInListMovement form) {
        final List<ItemDtoMovement> itemDtoMovementList = form.getItems()
                .stream()
                .map(this::checkAndChangeAmount)
                .filter(itemDto -> itemDto.getAmountForMovement() > 0)
                .toList();
        final ItemsDtoInListMovement formForMovement = new ItemsDtoInListMovement();
        formForMovement.setItems(itemDtoMovementList);
        return formForMovement;
    }

    private ItemDtoMovement checkAndChangeAmount(ItemDtoMovement itemDto) {
        Objects.requireNonNull(itemDto.getId());
        if (itemDto.getAmountForMovement() > itemDto.getAmount()) {
            itemDto.setAmountForMovement(0);
        }
        return itemDto;
    }

    public void getAndChangeOrCreate(ItemDtoMovement itemDto, Warehouse warehouse, Person person) {
        Optional<Item> itemOptional = itemRepository.findById(itemDto.getId());
        final Item item = itemOptional.orElseThrow(() ->
                new NoSuchElementException("Item with id " + itemDto.getId() + " not found"));
        if (item.getAmount() == itemDto.getAmountForMovement()) {
            item.setWarehouse(warehouse);
            item.setPerson(person);
            itemRepository.save(item);
            wareMovementRecordService.createAndSave(item, Movement.TRANSFER);
        } else {
            item.setAmount(item.getAmount() - itemDto.getAmountForMovement());
            copyFieldsCreateItemAndSave(item, warehouse, person, itemDto.getAmountForMovement());
        }
    }

    private void copyFieldsCreateItemAndSave(final Item item, Warehouse warehouse, Person person,
                                             int amountForMovement) {
        final Item itemNew = new Item();
        itemNew.setPrice(item.getPrice());
        itemNew.setName(item.getName());
        itemNew.setProductionYear(item.getProductionYear());
        itemNew.setInvoice(item.getInvoice());
        itemNew.setDeploymentDate(item.getDeploymentDate());
        itemNew.setInventoryNumber(item.getInventoryNumber());
        itemNew.setSerialNumber(item.getSerialNumber());
        itemNew.setWareGroup(item.getWareGroup());
        itemNew.setPerson(person);
        itemNew.setWarehouse(warehouse);
        itemNew.setAmount(amountForMovement);
        itemRepository.save(itemNew);
        List<WareMovementRecord> records = item.getRecords();
        System.out.println(records);
        wareMovementRecordService.copyRecordsCreateAndSave(records, itemNew);
        wareMovementRecordService.createAndSave(itemNew, Movement.TRANSFER);
    }
}
