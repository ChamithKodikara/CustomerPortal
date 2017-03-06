package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.UserDetail;
import com.vgates.customerportal.util.MethodResult;

/**
 * @author Chamith Kodikara
 */
public interface UserDetailDAO {

    MethodResult addNewUserDetail(UserDetail userDetail);

    MethodResult updateUserDetail(UserDetail userDetail);

    MethodResult changeUserStatus(boolean status, long userID);

    UserDetail findUserDetailByID(long id);

    UserDetail findUserDetailForActiveLogin();

    boolean loginUser(String userName, String password);

    boolean logoutUser(String userName);

    boolean logoutAllUsers();
}
