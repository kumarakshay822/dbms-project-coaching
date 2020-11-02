package com.dbms.coaching.services;

import com.dbms.coaching.models.MyUserDetails;
import com.dbms.coaching.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public String findLoggedInUsername() {
        Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (myUserDetails instanceof MyUserDetails) {
            return ((MyUserDetails) myUserDetails).getUser().getUsername();
        }
        return null;
    }

    @Override
    public int findLoggedInUserId() {
        Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (myUserDetails instanceof MyUserDetails) {
            return ((MyUserDetails) myUserDetails).getUser().getUserId();
        }
        return 0;
    }

    @Override
    public String findLoggedInName() {
        Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (myUserDetails instanceof MyUserDetails) {
            User user = ((MyUserDetails) myUserDetails).getUser();
            return user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();
        }
        return null;
    }

    @Override
    public String findLoggedInUserRole() {
        Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (myUserDetails instanceof MyUserDetails) {
            String smallRole = ((MyUserDetails) myUserDetails).getUser().getSmallRole();
            return smallRole;
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        MyUserDetails myUserDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                myUserDetails, password, myUserDetails.getAuthorities());
        // TODO: Validate email address (later)
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    @Override
    public void autoLogout() {
        SecurityContextHolder.clearContext();
    }

}
