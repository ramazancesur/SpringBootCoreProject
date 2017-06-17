package com.svlada.securty;

import com.svlada.entity.User;

import java.util.Optional;

/**
 * Created by ramazancesur on 16/06/2017.
 */
public interface UserService {
    public Optional<User> getByUsername(String username);
}
