package com.example.studentplacementapp.data;


import android.content.Context;
import android.widget.Toast;

import com.example.studentplacementapp.DatabaseHelper;
import com.example.studentplacementapp.MainActivity;
import com.example.studentplacementapp.User;
import com.example.studentplacementapp.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private final DatabaseHelper databaseHelper;

    public LoginDataSource(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public Result<LoggedInUser> login(String email, String password) {
        try {
            if (databaseHelper.checkUser(email, password)) {
                // Login Success
                LoggedInUser fakeUser =
                        new LoggedInUser(
                                java.util.UUID.randomUUID().toString(),
                                email);
                // TODO: handle loggedInUser authentication
                return new Result.Success<>(fakeUser);
            } else {
                // Login Failed
                return new Result.Error(new IOException("Invalid email or password"));
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}