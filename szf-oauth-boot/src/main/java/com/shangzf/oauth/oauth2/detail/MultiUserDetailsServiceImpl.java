package com.shangzf.oauth.oauth2.detail;

import com.google.common.collect.Sets;
import com.shangzf.authority.api.dto.RoleDTO;
import com.shangzf.authority.api.remote.IRoleRemoteService;
import com.shangzf.oauth.entity.UserJwt;
import com.shangzf.oauth.multi.MultiAuthentication;
import com.shangzf.oauth.multi.MultiAuthenticationContext;
import com.shangzf.oauth.multi.authenticator.MultiAuthenticator;
import com.shangzf.user.api.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service("userDetailService")
public class MultiUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IRoleRemoteService roleRemoteService;
    private final List<MultiAuthenticator> authenticators;

    public MultiUserDetailsServiceImpl(List<MultiAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MultiAuthentication authentication = MultiAuthenticationContext.get();
        if (Objects.isNull(authentication)) {
            authentication = new MultiAuthentication();
        }
        authentication.setUsername(username);
        UserDTO userDTO = this.authenticate(authentication);
        if (Objects.isNull(userDTO)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new UserJwt(
                userDTO.getUsername(),
                userDTO.getSecret(),
                !userDTO.getDeleted(),
                userDTO.getAccountNonExpired(),
                userDTO.getCredentialsNonExpired(),
                userDTO.getAccountNonLocked(),
                this.obtainGrantedAuthorities(userDTO), userDTO.getId());
    }

    /**
     * 获得登录者所有角色的权限集合.
     */
    private Collection<? extends GrantedAuthority> obtainGrantedAuthorities(UserDTO dto) {
        try {
            List<RoleDTO> roles = roleRemoteService.getRolesByUserId(dto.getId());
            log.info("user:{},roles:{}", dto.getUsername(), roles);
            return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Sets.newHashSet(new SimpleGrantedAuthority("NONE"));
        }
    }

    private UserDTO authenticate(MultiAuthentication authentication) {
        for (MultiAuthenticator authenticator : authenticators) {
            if (authenticator.support(authentication)){
                return authenticator.authenticate(authentication);
            }
        }
        return null;
    }
}
