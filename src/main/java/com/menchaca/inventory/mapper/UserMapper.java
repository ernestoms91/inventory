package com.menchaca.inventory.mapper;

import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.User;
import com.menchaca.inventory.model.dto.BusinessOfficeDTO;
import com.menchaca.inventory.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User UserDTOToUser(UserDTO userDTO);
    UserDTO UserToUserDTO(User user);
}
