package com.example.backend.repositories;

import com.example.backend.model.entities.Long;
import com.example.backend.model.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Long, Long> {
  Optional<Long> findByUser(User user);
}
