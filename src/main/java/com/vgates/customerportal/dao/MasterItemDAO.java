package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.MasterItem;

/**
 * Created by Chamith on 11/21/2016.
 */
public interface MasterItemDAO {

    void addNewMasterItem(MasterItem item);

    void updateMasterItem(MasterItem item);

    void changeItemStatus(boolean status, long itemID);

    MasterItem findMasterItemByID(long id);
}
