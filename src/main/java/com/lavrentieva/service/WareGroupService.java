package com.lavrentieva.service;

import com.lavrentieva.model.WareGroup;
import com.lavrentieva.repository.WareGroupRepository;
import com.lavrentieva.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WareGroupService {
    private final WareGroupRepository wareGroupRepository;

    @Autowired
    public WareGroupService(final WareGroupRepository wareGroupRepository) {
        this.wareGroupRepository = wareGroupRepository;
    }

    public Iterable<WareGroup> getAll(){
        return wareGroupRepository.findAll();
    }
}
