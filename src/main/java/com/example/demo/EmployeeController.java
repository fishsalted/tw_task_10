package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EmployeeController {

    private static Map<Integer, Employee> employeeMap = Collections.synchronizedMap(new HashMap<>());

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee retrieveEmployee(@PathVariable Integer id) {
        return employeeMap.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable Integer id) {
        employeeMap.remove(id);
        return "success";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> getEmployeeList() {
        return new ArrayList<>(employeeMap.values());
    }

    @RequestMapping(value = "/create_employee", method = RequestMethod.POST)
    public String postEmployee(@ModelAttribute Employee employee) {
        if(employeeMap.containsKey(employee.getId())) {
            return "employee already exists";
        }
        employeeMap.put(employee.getId(), employee);
        return "success";
    }

    @RequestMapping(value = "/modify_employee", method = RequestMethod.PUT)
    public String putEmployee(@ModelAttribute Employee employee) {
        if(!employeeMap.containsKey(employee.getId())) {
            return "employee does not exist";
        }
        employeeMap.put(employee.getId(), employee);
        return "success";
    }

}
