package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.Model.User;
import com.pembukuan_cv_abba_barokah.DAO.UserDao;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public User login(String username, String password) {
        User user = userDao.getByUsername(username);

        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public User getUserById(int id) {
        return userDao.getById(id); 
    }

    public boolean updateUser(User user) {
        return userDao.update(user); 
    }

    public boolean deleteUser(int id) {
        return userDao.delete(id); 
    }
}
