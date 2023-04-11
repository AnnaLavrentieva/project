package com.lavrentieva.service;

import com.lavrentieva.model.Item;
import com.lavrentieva.model.Movement;
import com.lavrentieva.model.WareMovementRecord;
import com.lavrentieva.repository.WareMovementRecordRepository;
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

    public void saveAllFromList (List<WareMovementRecord> records){
        wareMovementRecordRepository.saveAll(records);
    }
    public void createAddForItemListByArrivalAndSave(final List<Item> itemList) {
        itemList.forEach(this::createAndSaveForItemByArrival);
    }

    private void createAndSaveForItemByArrival(final Item item) {
        final WareMovementRecord record = new WareMovementRecord();
        record.setDate(new Date());
        record.setMovement(Movement.ARRIVAL);
        record.setPerson(item.getPerson());
        record.setWarehouse(item.getWarehouse());
        record.setWare(item);
        wareMovementRecordRepository.save(record);
    }

}
