package com.svlada.user.service;


import com.svlada.entity.User;
import com.svlada.securty.UserService;
import com.svlada.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by ramazancesur on 16/06/2017.
 */
@Service
public class DatabaseUserService implements UserService {
  private final UserRepository userRepository;

  @Autowired
  public DatabaseUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserRepository getUserRepository() {
    return userRepository;
  }

  @Override
  public Optional<User> getByUsername(String username) {
    return this.userRepository.findByUsername(username);
  }
}
