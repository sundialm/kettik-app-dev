package kz.iitu.kettik.authentication.mappers;

import kz.iitu.kettik.authentication.DTO.UserDTO;
import kz.iitu.kettik.authentication.entities.User;
import kz.iitu.kettik.interfaces.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO> {

    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    @Autowired
    public UserMapper(ModelMapper mapper, PasswordEncoder encoder) {
        super(User.class, UserDTO.class);
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @PostConstruct
    public void setMapper() {
        mapper
                .createTypeMap(UserDTO.class, User.class)
                .addMappings(mapper -> mapper.skip(User::setPassword))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(UserDTO source, User destination) {
        String password = source.getPassword();
        destination.setPassword(password);
//        destination.setPassword(encoder.encode(password));
    }
}
