package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.MasterService;
import com.vgates.customerportal.util.MethodResult;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
public interface MasterServiceDAO {

    MethodResult addNewMasterService(MasterService service);

    MethodResult updateMasterService(MasterService service);

    MethodResult changeServiceStatus(boolean status, long paymentID);

    MasterService findMasterServiceByID(long id);

    List<MasterService> findMasterServiceByName(String name);

    List<MasterService> findAllActiveMasterService();

    List<MasterService> findMasterService(String name, String category);
}
