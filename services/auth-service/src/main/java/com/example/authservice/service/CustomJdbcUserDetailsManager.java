package com.example.authservice.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class CustomJdbcUserDetailsManager extends JdbcUserDetailsManager {
    public CustomJdbcUserDetailsManager(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();

        return jdbcTemplate.query(getUsersByUsernameQuery(), (ResultSet rs, int rowNum) -> {
            Long id = rs.getLong(1);
            String email = rs.getString(2);
            String password = rs.getString(3);
            boolean enabled = rs.getBoolean(4);

            return new CustomUserDetails(id, email, password, enabled);
        }, username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetails> userDetails = loadUsersByUsername(username);
        if (userDetails.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return userDetails.get(0);
    }
}
