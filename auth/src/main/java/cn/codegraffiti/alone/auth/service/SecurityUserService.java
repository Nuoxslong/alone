package cn.codegraffiti.alone.auth.service;

import cn.codegraffiti.alone.auth.domain.security.AloneSecurityUser;
import cn.codegraffiti.alone.auth.entity.User;
import cn.codegraffiti.alone.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User queryEntity = new User();
        queryEntity.setUsername(username);
        Example<User> example = Example.of(queryEntity);
        Optional<User> userOptional = this.userRepository.findOne(example);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        User user = userOptional.get();
        List<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new AloneSecurityUser(user.getUsername(), user.getPassword(), authorities);
    }
}
