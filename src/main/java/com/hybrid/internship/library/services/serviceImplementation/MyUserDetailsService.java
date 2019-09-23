package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.dtos.MyUserPrincipal;
import com.hybrid.internship.library.models.User;
import com.hybrid.internship.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username).orElseGet(() -> null);
        if(user == null)
            throw new UsernameNotFoundException(username);

        return new MyUserPrincipal(user);
    }
}
