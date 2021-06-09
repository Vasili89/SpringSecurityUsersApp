package by.kostopravov.usersapp.repository;

import by.kostopravov.usersapp.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersDao extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUserName(String userName);
}
