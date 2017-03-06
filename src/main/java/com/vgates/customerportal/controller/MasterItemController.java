package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.MasterItemDAO;
import com.vgates.customerportal.dao.impl.MasterItemDAOImpl;
import com.vgates.customerportal.model.MasterItem;
import com.vgates.customerportal.util.MethodResult;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
public class MasterItemController {
    private MasterItemDAO masterItemDAO;

    public MasterItemController() {
        masterItemDAO = new MasterItemDAOImpl();
    }

    public MethodResult addNewItemDetail(MasterItem item) {
        return masterItemDAO.addNewMasterItem(item);
    }

    public MethodResult updateItemDetail(MasterItem item) {
        return masterItemDAO.updateMasterItem(item);
    }

    public MethodResult changeItemStatus(boolean status, long id) {
        return masterItemDAO.changeItemStatus(status, id);
    }

    public MasterItem searchItemByID(long id) {
        return masterItemDAO.findMasterItemByID(id);
    }

    public MasterItem searchItemByName(String name) {
        return masterItemDAO.findMasterItemByName(name);
    }

    public List<MasterItem> getAllActiveItems() {
        return masterItemDAO.findAllActiveMasterItems();
    }
}
