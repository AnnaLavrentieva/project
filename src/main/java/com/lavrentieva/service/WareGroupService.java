package com.lavrentieva.service;

import com.lavrentieva.model.WareGroup;
import com.lavrentieva.repository.WareGroupRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WareGroupService {
    private final WareGroupRepository wareGroupRepository;

    @Autowired
    public WareGroupService(final WareGroupRepository wareGroupRepository) {
        this.wareGroupRepository = wareGroupRepository;
    }

    public List<String> getAllWareGroupsNames(){
        return wareGroupRepository.getAllId();
    }

    public WareGroup getById(@NonNull final String id){
        return wareGroupRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("WareGroup with id "+id+ "not found"));
    }
}
