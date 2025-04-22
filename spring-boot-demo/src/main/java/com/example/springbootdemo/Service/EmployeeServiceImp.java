package com.example.springbootdemo.Service;

import com.example.springbootdemo.exceptions.EmployeeExceptions;
import com.example.springbootdemo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service

public class EmployeeServiceImp implements EmployeeService {
    List<Employee> employees = new ArrayList<Employee>();

    @Override
    public Employee save(Employee employee) {
        //validations here
        if (employee.getEmployeeId() == null || employee.getEmail() == null) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return List.of(employees.toArray(new Employee[employees.size()]));
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employees.stream().filter(employee -> employee.getEmployeeId().equals(id)).
                findFirst().orElseThrow(()-> new EmployeeExceptions("Employee not found with Id:"+id));
    }

    @Override
    public String deleteEmployeeById(String id) {
        Employee employee = getEmployeeById(id);
        employees.remove(employee);
        return "Employee deleted successfully";
    }


}
