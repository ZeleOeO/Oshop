package com.zele.ishop.repository;

import com.zele.ishop.entity.Role;
import com.zele.ishop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
}
