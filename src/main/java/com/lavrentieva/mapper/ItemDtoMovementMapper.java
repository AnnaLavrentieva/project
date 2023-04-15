package com.lavrentieva.mapper;

import com.lavrentieva.dto.InvoiceDto;
import com.lavrentieva.dto.ItemDtoMovement;
import com.lavrentieva.model.Invoice;
import com.lavrentieva.model.Item;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemDtoMovementMapper {

    public List<ItemDtoMovement> mapToDtoInListFromModel(final List<Item> items) {
        return items.stream()
                .map(this::mapToDtoFromModel)
                .collect(Collectors.toList());
    }

    private ItemDtoMovement mapToDtoFromModel(final Item item) {
        final ItemDtoMovement itemDto = new ItemDtoMovement();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDeploymentDate(item.getDeploymentDate());
        itemDto.setAmount(item.getAmount());
        itemDto.setPerson(item.getPerson().getName());
        itemDto.setWarehouse(item.getWarehouse().getName());
        itemDto.setWareGroup(item.getWareGroup().getName());
        return itemDto;
    }
}
