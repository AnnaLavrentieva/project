package com.lavrentieva.mapper;

import com.lavrentieva.dto.ItemDTO;
import com.lavrentieva.model.Item;
import com.lavrentieva.model.OldData;

public final class ItemMapper {
    private ItemMapper() {
    }

    public static ItemDTO mapToNamePriceDTO (OldData item){
        return new ItemDTO(item.getName(), item.getPrice());
    }
}
