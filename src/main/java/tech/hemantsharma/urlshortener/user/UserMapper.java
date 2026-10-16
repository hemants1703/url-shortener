package tech.hemantsharma.urlshortener.user;

import org.mapstruct.Mapper;
import tech.hemantsharma.urlshortener.user.dtos.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toUserResponseDto(UserEntity userEntity);
}
