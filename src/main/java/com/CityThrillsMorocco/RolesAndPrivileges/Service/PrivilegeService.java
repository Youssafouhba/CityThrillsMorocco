package com.CityThrillsMorocco.RolesAndPrivileges.Service;

import com.CityThrillsMorocco.RolesAndPrivileges.Models.Privilege;
import com.CityThrillsMorocco.RolesAndPrivileges.Repository.PrivilegeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PrivilegeService {
    private final PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository1) {
        this.privilegeRepository = privilegeRepository1;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

}
