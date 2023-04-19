package com.lavrentieva.repository;

import com.lavrentieva.model.WareMovementRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareMovementRecordRepository extends CrudRepository<WareMovementRecord, String> {
}
