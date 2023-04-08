package com.lavrentieva.repository;

import com.lavrentieva.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item,String> {
//    void updatePrice(String id, int price);
}
