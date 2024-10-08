package com.luizmedeirosn.futs3.repositories;

import com.luizmedeirosn.futs3.entities.CustomUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  Optional<CustomUser> findByUsername(String username);
}
