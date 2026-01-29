package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.User;
import com.pembukuan_cv_abba_barokah.Service.UserService;

import java.lang.reflect.Constructor;
import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController() {
        this.userService = createUserService();
    }

    /* ===================== AUTH ===================== */

    public User login(String username, String password) {
        if (username == null || username.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        return userService.login(username, password);
    }

    /* ===================== READ ===================== */

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User getUserById(int id) {
        if (id <= 0) return null;
        return userService.getUserById(id);
    }

    /* ===================== UPDATE ===================== */

    public boolean updateUser(User user) {
        if (!isValidUser(user)) return false;
        return userService.updateUser(user);
    }

    /* ===================== DELETE ===================== */

    public boolean deleteUser(int id) {
        if (id <= 0) return false;
        return userService.deleteUser(id);
    }

    /* ===================== INTERNAL ===================== */

    private UserService createUserService() {
        try {
            Constructor<UserService> ctor =
                    UserService.class.getDeclaredConstructor();
            ctor.setAccessible(true); // 🚨 bypass private
            return ctor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Gagal inisialisasi UserService", e);
        }
    }

    private boolean isValidUser(User user) {
        if (user == null) return false;
        if (user.getId() <= 0) return false;
        if (user.getUsername() == null || user.getUsername().isEmpty()) return false;
        if (user.getPassword() == null || user.getPassword().isEmpty()) return false;
        if (user.getRole() == null || user.getRole().isEmpty()) return false;
        return true;
    }
}