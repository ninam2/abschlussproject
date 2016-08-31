package axelspringer.controllers;

import axelspringer.models.User;
import axelspringer.models.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;
import java.util.StringJoiner;

/**
 * Class UserController
 */
@Controller
public class UserController {

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    // Wire the UserDao used inside this controller.
    @Autowired
    private UserDao userDao;

    /**
     * Create a new user with an auto-generated id and email and name as passed
     * values.
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public String create(String email, String name) {
        try {
            User user = new User(email, name);
            userDao.create(user);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created!";
    }

    /**
     * Delete the user with the passed id.
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        } catch (Exception ex) {
            return "Error deleting the user: " + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * Retrieve the id for the user with the passed email address.
     */
    @RequestMapping(value = "/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String userId;
        try {
            User user = userDao.getByEmail(email);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            return "User not found: " + ex.toString();
        }
        return "The user id is: " + userId;
    }


    @RequestMapping(value = "/findAll")
    @ResponseBody
    public String findAllUsers() {
        String username = "";
        try {
            List<User> user = userDao.getAll();
            StringJoiner usernames = new StringJoiner(", ");


            for (User anUser : user) {
                usernames.add(anUser.getName());

            }
            return usernames.toString();

        } catch (Exception ex) {
            return "User not found: " + ex.toString();
        }
    }

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    /**
     * Update the email and the name for the user indentified by the passed id.
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateName(long id, String email, String name) {
        try {
            User user = userDao.getById(id);
            user.setEmail(email);
            user.setName(name);
            userDao.update(user);
        } catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }


} // class UserController
