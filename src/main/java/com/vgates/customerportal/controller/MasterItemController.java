package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.MasterItemDAO;
import com.vgates.customerportal.dao.impl.MasterItemDAOImpl;
import com.vgates.customerportal.model.MasterItem;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
public class MasterItemController {
    private MasterItemDAO masterItemDAO;

    public MasterItemController() {
        masterItemDAO = new MasterItemDAOImpl();
    }

    public void addNewItemDetail(MasterItem item) {
        masterItemDAO.addNewMasterItem(item);
    }

    public void updateItemDetail(MasterItem item) {
        masterItemDAO.updateMasterItem(item);
    }

    public void changeItemStatus(boolean status, long id) {
        masterItemDAO.changeItemStatus(status, id);
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
