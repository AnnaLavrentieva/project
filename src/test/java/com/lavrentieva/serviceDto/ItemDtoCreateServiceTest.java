package com.lavrentieva.serviceDto;

import com.lavrentieva.dto.ItemDtoCreate;
import com.lavrentieva.service.PersonService;
import com.lavrentieva.service.WareGroupService;
import com.lavrentieva.service.WarehouseService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ItemDtoCreateServiceTest {

    @Mock
    private WareGroupService wareGroupService;

    @Mock
    private WarehouseService warehouseService;

    @Mock
    private PersonService personService;


    private ItemDtoCreateService itemDtoCreateService;


    @BeforeEach
    public void setUp() {
        itemDtoCreateService = new ItemDtoCreateService(wareGroupService, warehouseService, personService);
    }


    @Test
    public void testAddToCache1Record() {
        ItemDtoCreate item = new ItemDtoCreate();
        item.setId(String.valueOf(3));
        item.setName("item3");
        item.setSerialNumber("SN123");
        item.setInventoryNumber("INV123");
        item.setProductionYear(2022);
        item.setAmount(1);
        item.setPrice(100.0);
        int initialCacheSize = itemDtoCreateService.getAllFromCache().size();
        itemDtoCreateService.addToCache(item);
        assertEquals(initialCacheSize + 1, itemDtoCreateService.getAllFromCache().size());
        assertTrue(itemDtoCreateService.getFromCache(String.valueOf(3)).equals (item));
    }

    @Test
    public void testAddToCache2Records() {
        ItemDtoCreate item1 = new ItemDtoCreate();
        item1.setId(String.valueOf(1));
        item1.setName("item1");
        item1.setSerialNumber("SN123");
        item1.setInventoryNumber("INV123");
        item1.setProductionYear(2022);
        item1.setAmount(1);
        item1.setPrice(100.0);

        ItemDtoCreate item2 = new ItemDtoCreate();
        item2.setId(String.valueOf(2));
        item2.setName("item2");
        item2.setSerialNumber("SN123456");
        item2.setInventoryNumber("INV123456");
        item2.setProductionYear(2023);
        item2.setAmount(1);
        item2.setPrice(100500.0);

        itemDtoCreateService.addToCache(item1);
        int initialCacheSize = itemDtoCreateService.getAllFromCache().size();
        itemDtoCreateService.addToCache(item2);
        assertEquals(initialCacheSize+1, itemDtoCreateService.getAllFromCache().size());
        assertTrue(itemDtoCreateService.getFromCache(String.valueOf(1)).equals (item1));
        assertTrue(itemDtoCreateService.getFromCache(String.valueOf(2)).equals (item2));
    }

    @Test
    public void testGetExceptionFromCache(){
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            itemDtoCreateService.getFromCache(String.valueOf(100500));
        });
        assertEquals("Item with id" + "100500" + "not found", exception.getMessage());
    }

    @Test
    public void testGetTotalSumFromCache(){
        assertEquals(100700.0, itemDtoCreateService.getTotalSumFromCache());
    }

    @Test
    public void testRemoveFromCache(){
        int initialCacheSize = itemDtoCreateService.getAllFromCache().size();
        itemDtoCreateService.deleteFromCache(String.valueOf(2));
        assertEquals(initialCacheSize-1, itemDtoCreateService.getAllFromCache().size());
        assertEquals(200.0, itemDtoCreateService.getTotalSumFromCache());
    }

}