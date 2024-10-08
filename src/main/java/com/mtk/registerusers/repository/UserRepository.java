package com.mtk.registerusers.repository;

import com.mtk.registerusers.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	User findUserByEmail(String email);
}
