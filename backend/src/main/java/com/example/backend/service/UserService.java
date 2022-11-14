package com.example.backend.service;

import com.example.backend.model.entities.User;
import java.util.Optional;

public interface UserService {
  Optional<User> findUserByName(String name);

  User saveUser(User user);
}
