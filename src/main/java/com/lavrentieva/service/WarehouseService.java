package com.lavrentieva.service;

import com.lavrentieva.model.Warehouse;
import com.lavrentieva.repository.WarehouseRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse getById(@NonNull final String id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Warehouse not found"));
    }
    public List<String> getAllWarehousesNames(){
        return warehouseRepository.getAllId();
    }
}
