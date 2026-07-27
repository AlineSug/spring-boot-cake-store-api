package com.cakes.store.user;

import com.cakes.store.config.CryptPasswords;
import jakarta.persistence.EntityNotFoundException;
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
        user.setRole(Role.USER);
        String cryptPassword = CryptPasswords.criptografia(user.getPassword());
        user.setPassword(cryptPassword);
        repository.save(user);
        return userMapper.toDTO(user);
    }

    public UserData updateRole(Long id, RoleUpdateDTO dto) {
        User user = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found"));
        user.setRole(dto.getRole());
        repository.save(user);
        return userMapper.toDTO(user);
    }
}
