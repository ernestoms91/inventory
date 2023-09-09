package com.menchaca.inventory.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.menchaca.inventory.model.Item;
import com.menchaca.inventory.model.dto.ItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemMapper {
    @Mapping(source = "id_department", target = "businessOffice.id")
//    @Mapping(source = "type", target = "type")
    Item itemDTOToItem(ItemDTO itemDTO);
    @Mapping(source = " businessOffice.id", target = "id_department")
    ItemDTO ItemToItemDTO(Item item);
}
