package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.UserDetailDAO;
import com.vgates.customerportal.dao.impl.UserDetailDAOImpl;
import com.vgates.customerportal.encrypter.PasswordEncrypter;
import com.vgates.customerportal.model.UserDetail;

/**
 * @author Chamith Kodikara
 */
public class UserDetailController {

    private UserDetailDAO userDetailDAO;
    private PasswordEncrypter encrypter;

    public UserDetailController() {
        userDetailDAO = new UserDetailDAOImpl();
        encrypter = new PasswordEncrypter();
    }

    public void addNewUserDetail(UserDetail userDetail) {
        userDetail.setPassword(encrypter.getEncryptedPassword(userDetail.getPassword()));
        userDetailDAO.addNewUserDetail(userDetail);
    }

    public void updateNewUserDetail(UserDetail userDetail) {
        userDetail.setPassword(encrypter.getEncryptedPassword(userDetail.getPassword()));
        userDetailDAO.updateUserDetail(userDetail);
    }

    public void updateUserStatus(boolean status, long userID) {
        userDetailDAO.changeUserStatus(status, userID);
    }

    public UserDetail searchUserByID(long userID) {
        return userDetailDAO.findUserDetailByID(userID);
    }

    public UserDetail findUserDetailForActiveLogin() {
        return userDetailDAO.findUserDetailForActiveLogin();
    }

    public boolean loginUser(String userName, String password) {
        return userDetailDAO.loginUser(userName, encrypter.getEncryptedPassword(password));
    }

    public boolean logoutUser(String userName) {
        return userDetailDAO.logoutUser(userName);
    }

    public boolean logoutAllUser() {
        return userDetailDAO.logoutAllUsers();
    }
}
