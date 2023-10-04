package com.menchaca.inventory.mapper;

import com.menchaca.inventory.model.BusinessOffice;
import com.menchaca.inventory.model.User;
import com.menchaca.inventory.model.dto.BusinessOfficeDTO;
import com.menchaca.inventory.model.dto.UserDTO;
import com.menchaca.inventory.model.dto.UserDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User UserDTOToUser(UserDTO userDTO);
    UserDTO UserToUserDTO(User user);
    User UserDetailDTOToUser(UserDetailDTO userDetailDTO);
    UserDetailDTO UserToUserDetailDTO(User user);
}
