package com.lavrentieva.repository;

import com.lavrentieva.model.WareGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WareGroupRepository extends CrudRepository<WareGroup,String> {

    @Query(value = "SELECT group_id FROM ware_groups", nativeQuery = true)
    public List<String> getAllId();
}
