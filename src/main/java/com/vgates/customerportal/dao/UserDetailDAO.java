package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.UserDetail;

/**
 * Created by Chamith on 11/21/2016.
 */
public interface UserDetailDAO {
    void addNewUserDetail(UserDetail userDetail);

    void updateUserDetail(UserDetail userDetail);

    void changeUserStatus(boolean status,long userID);

    UserDetail findUserDetailByID(long id);
}
