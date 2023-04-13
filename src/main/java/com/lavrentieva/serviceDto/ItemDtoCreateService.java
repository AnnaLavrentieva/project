package com.lavrentieva.serviceDto;

import com.lavrentieva.dto.ItemDtoCreate;
import com.lavrentieva.model.Item;
import com.lavrentieva.service.PersonService;
import com.lavrentieva.service.WareGroupService;
import com.lavrentieva.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ItemDtoCreateService {
    private static final List<ItemDtoCreate> CACHE = new LinkedList<>();
    private final WareGroupService wareGroupService;
    private final WarehouseService warehouseService;
    private final PersonService personService;

    @Autowired
    public ItemDtoCreateService(WareGroupService wareGroupService, WarehouseService warehouseService,
                                PersonService personService) {
        this.wareGroupService = wareGroupService;
        this.warehouseService = warehouseService;
        this.personService = personService;
    }


    public void addToCache(final ItemDtoCreate item) {
        final int index = getIndexFromCache(item.getId());
        if (index < 0) {
            CACHE.add(item);
        } else {
            CACHE.set(index, item);
        }
    }

    private int getIndexFromCache(final String id) {
        OptionalInt index = IntStream.range(0, CACHE.size())
                .filter(i -> CACHE.get(i).getId().equals(id))
                .findFirst();
        return index.orElse(-1);
    }

    public List<ItemDtoCreate> getAllFromCache() {
        return CACHE;
    }

    public double getTotalSumFromCache() {
        return CACHE.stream()
                .mapToDouble(itemDtoCreate -> itemDtoCreate.getAmount() * itemDtoCreate.getPrice())
                .sum();
    }

    public ItemDtoCreate getFromCache(final String id) {
        return CACHE.stream()
                .filter(itemDtoCreate -> itemDtoCreate.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Item with id" + id + "not found"));
    }

    public void deleteFromCache(final String id) {
        CACHE.removeIf(itemDtoCreate -> itemDtoCreate != null && itemDtoCreate.getId().equals(id));
    }

    public void clearCache() {
        CACHE.clear();
    }

    public List<Item> mapToModelInListFromDTO() {
        return CACHE.stream()
                .map(this::mapToModelFromDTO)
                .collect(Collectors.toList());
    }

    private Item mapToModelFromDTO(ItemDtoCreate itemDTOCreate) {
        final Item item = new Item();
        item.setName(itemDTOCreate.getName());
        item.setSerialNumber(itemDTOCreate.getSerialNumber());
        item.setInventoryNumber(itemDTOCreate.getInventoryNumber());
        item.setProductionYear(itemDTOCreate.getProductionYear());
        item.setDeploymentDate(itemDTOCreate.getDeploymentDate());
        item.setAmount(itemDTOCreate.getAmount());
        item.setPrice(itemDTOCreate.getPrice());
        final String idGroup = itemDTOCreate.getWareGroup();
        item.setWareGroup(wareGroupService.getById(idGroup));
        final String idWarehouse = itemDTOCreate.getWarehouse();
        item.setWarehouse(warehouseService.getById(idWarehouse));
        final String idPerson = itemDTOCreate.getPerson();
        item.setPerson(personService.getById(idPerson));
        return item;
    }
}
