package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.User;
import com.pembukuan_cv_abba_barokah.Service.UserService;

import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    // ===================== AUTH =====================

    /**
     * Proses login user
     */
    public User login(String username, String password) {
        return userService.login(username, password);
    }

    // ===================== READ =====================

    public List<User> tampilkanSemuaUser() {
        return userService.getAllUsers();
    }

    public User tampilkanUserById(int id) {
        return userService.getUserById(id);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiUser(
            int id,
            String username,
            String password,
            String role
    ) {
        String hashedPassword = User.hashPassword(password);

        User user = new User(
                id,
                username,
                hashedPassword,
                role
        );

        return userService.updateUser(user);
    }

    // ===================== DELETE =====================

    public boolean hapusUser(int id) {
        return userService.deleteUser(id);
    }
}