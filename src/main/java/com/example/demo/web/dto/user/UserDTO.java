package com.example.demo.web.dto.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.comm.util.UserAuthCd;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserDTO implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    private String USER_ID;
    private String USER_PW;
    private String USER_NM;
    private String USER_AUTH_CD;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection < GrantedAuthority > collectiotors = new ArrayList<>();
        collectiotors.add(() -> {
            if (UserAuthCd.ADMIN.getCode().equals(this.USER_AUTH_CD)) return UserAuthCd.ADMIN.getName();
            else return UserAuthCd.REGULAR.getName();
        });
        return collectiotors;
    }

    @Override
    public String getPassword() {
        return this.USER_PW;
    }

    @Override
    public String getUsername() {
        return this.USER_ID;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
