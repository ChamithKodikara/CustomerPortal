package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.UserDetail;

/**
 * @author Chamith Kodikara
 */
public interface UserDetailDAO {

    void addNewUserDetail(UserDetail userDetail);

    void updateUserDetail(UserDetail userDetail);

    void changeUserStatus(boolean status, long userID);

    UserDetail findUserDetailByID(long id);

    UserDetail findUserDetailForActiveLogin();

    boolean loginUser(String userName, String password);

    boolean logoutUser(String userName);

    boolean logoutAllUsers();
}
