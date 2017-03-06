package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.MasterServiceDAO;
import com.vgates.customerportal.dao.impl.MasterServiceDAOImpl;
import com.vgates.customerportal.model.MasterService;
import com.vgates.customerportal.util.MethodResult;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
public class MasterServiceController {
    private MasterServiceDAO serviceDAO;

    public MasterServiceController() {
        serviceDAO = new MasterServiceDAOImpl();
    }

    public MethodResult addNewServiceDetail(MasterService masterService) {
        return serviceDAO.addNewMasterService(masterService);
    }

    public MethodResult updateServiceDetails(MasterService masterService) {
        return serviceDAO.updateMasterService(masterService);
    }

    public MethodResult changeServiceStatus(boolean status, long serviceID) {
        return serviceDAO.changeServiceStatus(status, serviceID);
    }

    private MasterService searchServiceByID(long id) {
        return serviceDAO.findMasterServiceByID(id);
    }

    public List<MasterService> searchServiceByName(String name) {
        return serviceDAO.findMasterServiceByName(name);
    }

    public List<MasterService> searchService(String name, String category) {
        return serviceDAO.findMasterService(name, category);
    }

    public List<MasterService> searchAllActiveService() {
        return serviceDAO.findAllActiveMasterService();
    }

}
