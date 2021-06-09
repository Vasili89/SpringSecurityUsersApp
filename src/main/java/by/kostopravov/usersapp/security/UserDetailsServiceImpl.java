package by.kostopravov.usersapp.security;

import by.kostopravov.usersapp.model.UserAccount;
import by.kostopravov.usersapp.repository.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersDao usersDao;

    @Autowired
    public UserDetailsServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserAccount userAccount = usersDao.findByUserName(userName).orElseThrow(() ->
                new UsernameNotFoundException("User not found."));
        return SecurityUser.fromUserAccount(userAccount);
    }


}
