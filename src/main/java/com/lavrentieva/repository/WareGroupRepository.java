package com.lavrentieva.repository;

import com.lavrentieva.model.WareGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareGroupRepository extends CrudRepository<WareGroup,String> {
}
