package com.cryptos.cryptoalarms.repository;

import com.cryptos.cryptoalarms.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
