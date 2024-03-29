package com.ads.multiplebd.store.repositories;

import com.ads.multiplebd.store.models.ERole;
import com.ads.multiplebd.store.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}