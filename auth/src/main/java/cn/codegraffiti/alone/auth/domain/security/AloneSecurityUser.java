package cn.codegraffiti.alone.auth.domain.security;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class AloneSecurityUser extends User {

    public AloneSecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
