package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.MasterServiceDAO;
import com.vgates.customerportal.dao.impl.MasterServiceDAOImpl;
import com.vgates.customerportal.model.MasterService;

import java.util.List;

/**
 * Created by Chamith on 12/6/2016.
 */
public class MasterServiceController {
    private MasterServiceDAO serviceDAO;

    public MasterServiceController() {
        serviceDAO = new MasterServiceDAOImpl();
    }

    public void addNewServiceDetail(MasterService masterService) {
        serviceDAO.addNewMasterService(masterService);
    }

    public void updateServiceDetails(MasterService masterService) {
        serviceDAO.updateMasterService(masterService);
    }

    public void changeServiceStatus(boolean status, long serviceID) {
        serviceDAO.changeServiceStatus(status, serviceID);
    }

    private MasterService searchServiceByID(long id) {
        return serviceDAO.findMasterServiceByID(id);
    }

    public List<MasterService> searchServiceByName(String name) {
        return serviceDAO.findMasterServiceByName(name);
    }

}
