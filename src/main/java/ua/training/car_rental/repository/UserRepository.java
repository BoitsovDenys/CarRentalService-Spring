package ua.training.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.car_rental.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
