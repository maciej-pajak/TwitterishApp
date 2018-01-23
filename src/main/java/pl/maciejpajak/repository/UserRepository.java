package pl.maciejpajak.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.maciejpajak.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findFirstByUsernameIgnoreCase(String username);
    public boolean existsByUsernameIgnoreCase(String username);
    public boolean existsByEmail(String email);
    
}
