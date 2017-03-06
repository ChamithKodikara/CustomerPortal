package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.UserDetailDAO;
import com.vgates.customerportal.dao.impl.UserDetailDAOImpl;
import com.vgates.customerportal.encrypter.PasswordEncrypter;
import com.vgates.customerportal.model.UserDetail;
import com.vgates.customerportal.util.MethodResult;

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

    public MethodResult addNewUserDetail(UserDetail userDetail) {
        userDetail.setPassword(encrypter.getEncryptedPassword(userDetail.getPassword()));
        return userDetailDAO.addNewUserDetail(userDetail);
    }

    public MethodResult updateNewUserDetail(UserDetail userDetail) {
        userDetail.setPassword(encrypter.getEncryptedPassword(userDetail.getPassword()));
        return userDetailDAO.updateUserDetail(userDetail);
    }

    public MethodResult updateUserStatus(boolean status, long userID) {
        return userDetailDAO.changeUserStatus(status, userID);
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
