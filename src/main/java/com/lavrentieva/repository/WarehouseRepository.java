package com.lavrentieva.repository;

import com.lavrentieva.model.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse,String> {

    @Query(value = "SELECT warehouse_id FROM warehouses", nativeQuery = true)
    public List<String> getAllId();
}
