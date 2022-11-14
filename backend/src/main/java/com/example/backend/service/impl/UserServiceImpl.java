package com.example.backend.service.impl;

import com.example.backend.model.entities.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public Optional<User> findUserByName(String name) {
    return userRepository.findByName(name);
  }

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }
}
