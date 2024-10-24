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

    public Result<LoggedInUser> login(String name, String email, String password) {
        // Check if user exists
        if (databaseHelper.checkUser(email, password)) {
            // Login Success
            LoggedInUser loggedInUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            email);
            return new Result.Success<LoggedInUser>(loggedInUser);
        } else { // If user doesn't exit, register them.
            return this.register(name, email, password);
        }
    }

    public Result<LoggedInUser> register(String name, String email, String password) {
        // Check if user already exists
        if (!databaseHelper.checkUser(email)) {
            // Create new user
            User user = new User(name, email, password);
            if (databaseHelper.addUser(user)) {
                LoggedInUser loggedInUser =
                        new LoggedInUser(
                                java.util.UUID.randomUUID().toString(),
                                email);
                return new Result.Success<LoggedInUser>(loggedInUser);
            } else {
                return new Result.Error(new IOException("Invalid email or password"));
            }
        }
        return new Result.Error(new IOException("User already exists"));
    }

    public void logout() {
        // TODO: revoke authentication
    }
}