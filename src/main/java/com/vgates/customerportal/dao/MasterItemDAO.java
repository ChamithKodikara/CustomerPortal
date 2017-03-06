package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.MasterItem;
import com.vgates.customerportal.util.MethodResult;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
public interface MasterItemDAO {

    MethodResult addNewMasterItem(MasterItem item);

    MethodResult updateMasterItem(MasterItem item);

    MethodResult changeItemStatus(boolean status, long itemID);

    MasterItem findMasterItemByID(long id);

    MasterItem findMasterItemByName(String name);

    List<MasterItem> findAllActiveMasterItems();
}
