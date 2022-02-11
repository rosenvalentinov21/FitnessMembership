package com.FitnessMembership.FitnessMembership.Controllers;

import com.FitnessMembership.FitnessMembership.Entities.CardsAndServices.Card;
import com.FitnessMembership.FitnessMembership.Entities.CardsAndServices.Services;
import com.FitnessMembership.FitnessMembership.Entities.Employees.Employee;
import com.FitnessMembership.FitnessMembership.Entities.Employees.Role;
import com.FitnessMembership.FitnessMembership.Entities.Member;
import com.FitnessMembership.FitnessMembership.Repositories.Employees.EmployeeRepository;
import com.FitnessMembership.FitnessMembership.Repositories.Employees.RolesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employee")

public class EmployeeController {

    private final EmployeeRepository employeeRepo;
    private final RolesRepository roleRepo;

    public EmployeeController(EmployeeRepository employeeRepo, RolesRepository roleRepo) {
        this.employeeRepo = employeeRepo;
        this.roleRepo = roleRepo;
    }

    @GetMapping("/fetch/role")
    public List<Role> persistRole(){
        return roleRepo.findAll();
    }

    @GetMapping("/fetch")
    public List<Employee> persistEmployee(){
        return employeeRepo.findAll();
    }

    @PostMapping("/role/save")
    public ResponseEntity<?> saveRole(String roleName) {

        Role role = roleRepo.findRoleByName(roleName);
        if (role == null)
            return ResponseEntity.ok(roleRepo.save(new Role(roleName)));
            else
        return ResponseEntity.ok("there is already such service");
    }

    @PostMapping("/save")
    public  ResponseEntity<?> saveEmployee(String firstName, String lastName, String email, String roleName) {

        Role role = roleRepo.findRoleByName(roleName);
        Employee employee = employeeRepo.findEmployeeByEmail(email);
        if (employee == null) {
            if (employeeRepo.findEmployeeByRole(role) == null)
                return ResponseEntity.ok(employeeRepo.save(new Employee(firstName, lastName, email, role)));
         else
            return ResponseEntity.ok("there is such a service already");
    }
        else
            return ResponseEntity.ok("this email is already used");

    }

}


