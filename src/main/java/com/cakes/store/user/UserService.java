package com.cakes.store.user;

import com.cakes.store.config.CryptPasswords;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  implements UserDetailsService {


    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }

    public UserData createUser(UserData dto){
        User user = userMapper.toEntity(dto);
        String cryptPassword = CryptPasswords.criptografia(user.getPassword());
        user.setPassword(cryptPassword);
        repository.save(user);
        return userMapper.toDTO(user);
    }
}
