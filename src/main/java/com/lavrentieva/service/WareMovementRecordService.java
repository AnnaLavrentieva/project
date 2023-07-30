package com.lavrentieva.service;

import com.lavrentieva.model.Item;
import com.lavrentieva.model.Movement;
import com.lavrentieva.model.WareMovementRecord;
import com.lavrentieva.repository.WareMovementRecordRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WareMovementRecordService {
    private final WareMovementRecordRepository wareMovementRecordRepository;

    @Autowired
    public WareMovementRecordService(WareMovementRecordRepository wareMovementRecordRepository) {
        this.wareMovementRecordRepository = wareMovementRecordRepository;
    }

    public void createAddForItemListByArrivalAndSave(@NonNull final List<Item> itemList) {
        itemList.forEach(item -> createAndSave(item, Movement.ARRIVAL));
    }

    public void createAndSave(final Item item, Movement movement) {
        final WareMovementRecord record = new WareMovementRecord();
        record.setMovement(movement);
        record.setDate(new Date());
        record.setPerson(item.getPerson());
        record.setWarehouse(item.getWarehouse());
        record.setWare(item);
        wareMovementRecordRepository.save(record);
    }

    public void copyRecordsCreateAndSave(List<WareMovementRecord> records, Item itemNew) {
        for (WareMovementRecord record : records) {
            WareMovementRecord newRecord = new WareMovementRecord();
            newRecord.setMovement(record.getMovement());
            newRecord.setWarehouse(record.getWarehouse());
            newRecord.setPerson(record.getPerson());
            newRecord.setDate(record.getDate());
            newRecord.setWare(itemNew);
            wareMovementRecordRepository.save(newRecord);
        }
    }
}
