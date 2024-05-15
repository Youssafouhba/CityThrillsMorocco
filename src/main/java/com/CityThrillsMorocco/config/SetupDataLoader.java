package com.CityThrillsMorocco.config;

import com.CityThrillsMorocco.RolesAndPrivileges.Models.Privilege;
import com.CityThrillsMorocco.RolesAndPrivileges.Repository.PrivilegeRepository;
import com.CityThrillsMorocco.RolesAndPrivileges.Repository.RoleRepository;
import com.CityThrillsMorocco.RolesAndPrivileges.Models.Role;
import com.CityThrillsMorocco.RolesAndPrivileges.Service.PrivilegeService;
import com.CityThrillsMorocco.RolesAndPrivileges.Service.RoleService;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = true;

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PrivilegeService privilegeService;

    private final PasswordEncoder passwordEncoder;

    public SetupDataLoader(UserRepository userRepository, RoleService roleService, PrivilegeService privilegeService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.privilegeService = privilegeService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        Privilege readPrivilege
                = privilegeService.createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = privilegeService.createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege editPrivilege
                = privilegeService.createPrivilegeIfNotFound("EDIT_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege,editPrivilege);

        roleService.createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        roleService.createRoleIfNotFound("ROLE_CONTENT_MANAGER",Arrays.asList(readPrivilege,writePrivilege));
        roleService.createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
        Role adminRole = roleService.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFirstname("CityThrillsMorocco");
        user.setLastname("CityThrillsMorocco");
        user.setPassword(passwordEncoder.encode("city@pfs2024"));
        user.setEmail("CityThrillsMorocco@gmail.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);
        alreadySetup = true;
    }

}
