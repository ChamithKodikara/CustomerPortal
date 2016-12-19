package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.MasterService;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
public interface MasterServiceDAO {

    void addNewMasterService(MasterService service);

    void updateMasterService(MasterService service);

    void changeServiceStatus(boolean status, long paymentID);

    MasterService findMasterServiceByID(long id);

    List<MasterService> findMasterServiceByName(String name);
}
