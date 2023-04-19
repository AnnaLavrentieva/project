package com.lavrentieva.repository;

import com.lavrentieva.model.Item;
import com.lavrentieva.model.Person;
import com.lavrentieva.model.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {

    Page<Item> findByWarehouseAndPerson(Warehouse warehouse, Person person, Pageable pageable);

    Page<Item> findByWarehouse(Warehouse warehouse, Pageable pageable);

    Page<Item> findByPerson(Person person, Pageable pageable);

    Page<Item> findAll (Pageable pageable);
}
