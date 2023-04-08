package com.lavrentieva.repository;

import com.lavrentieva.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {
//    void updatePrice(String id, int price);
}
