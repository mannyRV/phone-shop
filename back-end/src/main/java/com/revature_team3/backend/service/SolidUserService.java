package com.revature_team3.backend.service;

import com.revature_team3.backend.entity.Customer;
import com.revature_team3.backend.entity.Employee;
import com.revature_team3.backend.entity.Manager;
import com.revature_team3.backend.repository.CustomerRepository;
import com.revature_team3.backend.repository.EmployeeRepository;
import com.revature_team3.backend.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SolidUserService implements UserService,UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ManagerRepository managerRepository;
    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public void register(Customer customer) {
        String bcryptHashString = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(bcryptHashString);
        customerRepository.save(customer);
    }

    public void register(Employee employee) {
        String bcryptHashString = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(bcryptHashString);
        employeeRepository.save(employee);
    }

    public void register(Manager manager) {
        String bcryptHashString = passwordEncoder.encode(manager.getPassword());
        manager.setPassword(bcryptHashString);
        managerRepository.save(manager);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        try {
            Customer customer= customerRepository.findByEmail(email);
            if(customer==null)throw new UsernameNotFoundException(email);
            ArrayList<GrantedAuthority> grantedAuthorities=new ArrayList<>();
            for(String authority:customer.getAuthorities()){
                grantedAuthorities.add(new SimpleGrantedAuthority(authority));
            }
            UserDetails userDetails=new org.springframework.security.core.userdetails.User(customer.getEmail(),customer.getPassword(),grantedAuthorities);
            return userDetails;
        } catch (UsernameNotFoundException noCustomer) {
            try {
                Employee employee = employeeRepository.findByEmail(email);
                if (employee == null) throw new UsernameNotFoundException(email);
                ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                for (String authority : employee.getAuthorities()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(authority));
                }
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassword(), grantedAuthorities);
                return userDetails;
            } catch (UsernameNotFoundException noEmployee) {
                try {
                    Manager manager = managerRepository.findByEmail(email);
                    if (manager == null) throw new UsernameNotFoundException(email);
                    ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                    for (String authority : manager.getAuthorities()) {
                        grantedAuthorities.add(new SimpleGrantedAuthority(authority));
                    }
                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(manager.getEmail(), manager.getPassword(), grantedAuthorities);
                    return userDetails;
                } catch (UsernameNotFoundException noUser) {
                    throw new UsernameNotFoundException("No user found with that email");
                }
            }
        }
    }
}
