package com.menchaca.inventory.mapper;

import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.dto.BusinessOfficeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BusinessOfficeMapper {


    BusinessOffice BusinessOfficeDTOToBusinessOffice(BusinessOfficeDTO businessOfficeDTO);
    BusinessOfficeDTO BusinessOfficeToBusinessOfficeDTO(BusinessOffice businessOffice);

}
