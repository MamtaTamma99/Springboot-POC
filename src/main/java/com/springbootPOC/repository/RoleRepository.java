package com.springbootPOC.repository;

import com.springbootPOC.dbo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mamta.t
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
