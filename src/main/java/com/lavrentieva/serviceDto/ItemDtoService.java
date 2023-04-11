package com.lavrentieva.serviceDto;

import com.lavrentieva.dto.ItemDto;
import com.lavrentieva.model.Item;
import com.lavrentieva.model.WareGroup;
import com.lavrentieva.service.PersonService;
import com.lavrentieva.service.WareGroupService;
import com.lavrentieva.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ItemDtoService {
    private static final List<ItemDto> CACHE = new LinkedList<>();
    private final WareGroupService wareGroupService;
    private final WarehouseService warehouseService;
    private final PersonService personService;

    @Autowired
    public ItemDtoService(WareGroupService wareGroupService, WarehouseService warehouseService,
                          PersonService personService) {
        this.wareGroupService = wareGroupService;
        this.warehouseService = warehouseService;
        this.personService = personService;
    }


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

    public List<Item> mapToModelInListFromDTO() {
        return CACHE.stream()
                .map(this::mapToModelFromDTO)
                .collect(Collectors.toList());
    }

    private Item mapToModelFromDTO(ItemDto itemDTO) {
        final Item item = new Item();
        item.setName(itemDTO.getName());
        item.setSerialNumber(itemDTO.getSerialNumber());
        item.setInventoryNumber(itemDTO.getInventoryNumber());
        item.setProductionYear(itemDTO.getProductionYear());
        item.setDeploymentDate(itemDTO.getDeploymentDate());
        item.setAmount(itemDTO.getAmount());
        item.setPrice(itemDTO.getPrice());
        final String idGroup = itemDTO.getWareGroup();
        item.setWareGroup(wareGroupService.getById(idGroup));
        final String idWarehouse = itemDTO.getWarehouse();
        item.setWarehouse(warehouseService.getById(idWarehouse));
        final String idPerson = itemDTO.getPerson();
        item.setPerson(personService.getById(idPerson));
        return item;
    }
}
