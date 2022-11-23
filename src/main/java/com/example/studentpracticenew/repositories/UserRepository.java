package com.example.studentpracticenew.repositories;

import com.example.studentpracticenew.entities.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,String>{

    @Query("from users where email=?1")
    public List<User> findByEMAIL(String email);

    @Query("from users where email=?1 and password=?2")
    public User findByEmailPassword(String email,String password);

}
