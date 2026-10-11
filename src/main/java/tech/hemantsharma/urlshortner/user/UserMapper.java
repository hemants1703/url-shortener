package tech.hemantsharma.urlshortner.user;

import org.mapstruct.Mapper;
import tech.hemantsharma.urlshortner.user.dtos.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toUserResponseDto(UserEntity userEntity);
}
