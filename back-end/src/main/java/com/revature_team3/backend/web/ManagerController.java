package com.revature_team3.backend.web;

import com.revature_team3.backend.entity.Employee;
import com.revature_team3.backend.entity.Manager;
import com.revature_team3.backend.repository.ManagerRepository;
import com.revature_team3.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private UserService managerService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/managers",
            produces = {"application/json","application/xml"}
    )
    public List getAllManagers() {
        return managerRepository.findAll();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/managers/{managerEmail}",
            produces = {"application/json","application/xml"}
    )
    public ResponseEntity<?> getByEmail(@PathVariable(name = "managerEmail") String managerEmail) {
        Manager manager = managerRepository.findByEmail(managerEmail);
        return ResponseEntity.ok(manager);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/phone-shop/managers",
            consumes = {"application/json","application/xml"}
    )
    public ResponseEntity<?> createManager(@RequestBody Manager manager) {
        managerService.register(manager);
        return ResponseEntity.status(201).body(manager);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/phone-shop/manager/{managerId}"
    )
    public ResponseEntity<?> updateManager(
            @PathVariable(name="managerId") int managerId,
            @RequestBody Manager manager
    ) {
        manager.setId(managerId);
        manager= (Manager) managerRepository.save(manager);
        return ResponseEntity.ok(manager);
    }

}
