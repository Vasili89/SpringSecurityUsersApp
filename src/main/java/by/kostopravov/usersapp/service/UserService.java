package by.kostopravov.usersapp.service;

import by.kostopravov.usersapp.model.UserAccount;
import by.kostopravov.usersapp.repository.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UsersDao usersDao;

    @Autowired
    public UserService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public List<UserAccount> getAllUserAccounts() {
        return usersDao.findAll();
    }

    public UserAccount getUserAccountById(Long id) {
        return usersDao.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User with id " + id + " not found."));
    }

    public void saveUserAccount(UserAccount userAccount) {
        usersDao.save(userAccount);
    }

    public UserAccount editUserAccount(Long id) {
        return getUserAccountById(id);
    }
}
