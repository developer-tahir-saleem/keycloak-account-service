package com.keycloak.accountservice.service.implementation;


import com.keycloak.accountservice.model.Device;
import com.keycloak.accountservice.repository.DeviceRepository;
import com.keycloak.accountservice.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service(value = "deviceService")
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device findOne(long id) {
        return deviceRepository.findById(id).get();
    }

    @Override
    public Optional<Device> findByUserIdAndDeviceToken(String id,String token) {

        return deviceRepository.findByUserId(id);
    }



    public List<Device> findAll() {
        List<Device> list = new ArrayList<>();
        deviceRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
        Optional<Device> device = deviceRepository.findById(id);
        if (device.isPresent()) {
//            deviceRepository.delete(device.get());
        } else {
        }
    }

    @Override
    public void insertOrUpdate(Device device) {
      Optional<Device> item =  findByHardwareId(device.getHardwareId());
        if(item.isPresent()){
            Device getdevice =   item.get();
            getdevice.setUserId(device.getUserId());
            getdevice.setType(device.getType());
            getdevice.setLoggedin(device.isLoggedin());
            getdevice.setDeviceToken(device.getDeviceToken());
            deviceRepository.save(getdevice);
        }else {
            deviceRepository.save(device);
        }
    }

    @Override
    public Optional<Device> findByHardwareId(String udid) {
        return deviceRepository.findByHardwareId(udid);
    }

    @Override
    public Device save(Device device) {
        return deviceRepository.save(device);
    }


}
