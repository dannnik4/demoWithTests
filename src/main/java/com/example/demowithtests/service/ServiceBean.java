package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.exeption.ResourceNotFoundException;
import com.example.demowithtests.util.exeption.ResourceWasDeletedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository repository;

    @Override
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        Employee employee = repository.findById(id)
                // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceNotFoundException::new);
         /*if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("Employee was deleted with id = " + id);
        }*/
        return employee;
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        return repository.findById(id).map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    entity.setAddress(employee.getAddress());
                    entity.setPhone(employee.getPhone());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        //repository.deleteById(id);
        Employee employee = repository.findById(id)
                // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceWasDeletedException::new);
        //employee.setIsDeleted(true);
        repository.delete(employee);
        //repository.save(employee);
    }

    @Override
    public void removeAll() {
        repository.deleteAll();
    }

    public List<Employee> getName(String name) {
        return repository.getEmployeeByName(name);
    }

    @Override
    public List<Employee> getCountry(String country) {
        return repository.getEmployeeByCountry(country);
    }

    @Override
    public List<Employee> getAllName(String name) {
        return repository.getAllByName(name);
    }

    @Override
    public List<Employee> getNameByPhone(String phone) {
        return repository.getEmployeeByPhone(phone);
    }

    @Override
    public Employee updateByPhone(String phone, Employee employee) {
        return null;
    }

    @Override
    public List<Employee> getEmployeeByPhoneU(String phone) {
        List<Employee> employees = repository.getEmployeeByPhoneU(phone);
        employees.stream().forEach(e -> System.err.println(e.getName() + " Доброго вечора,ми з Украины"));
        return employees;
    }

    @Override
    public Page<Employee> findByName(String name, int page, int size, List<String> sortList, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        return repository.findByName(name, pageable);
    }


    @Override
    public Page<Employee> findByAddress(String address, int page, int size, List<String> sortList, String sortOrder) {
        // create Pageable object using the page, size and sort details
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        // fetch the page object by additionally passing pageable with the filters
        return repository.findByAddress(address, pageable);
    }

    @Override
    public Page<Employee> findAll(int page, int size, List<String> sortList, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        return repository.findAll(pageable);
    }


    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }

    @Override
    public List<String> findDifferentCountries() {
        List<Employee> allEmployee = repository.findAll();
        return allEmployee.stream()
                .map(n -> n.getCountry())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllPhone() {
        List<Employee> usersPhones = repository.findAll();
        return usersPhones.stream()
                .map(p -> p.getPhone())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findShortNames() {
        List<Employee> shortNames = repository.findAll();
        return shortNames.stream()
                .map(n -> n.getName())
                .filter(n -> n.length() == 3)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findPhoneAndName() {
        List<Employee> nameAndPhone = repository.findAll();
        return nameAndPhone.stream()
                .map(n -> "name-" + n.getName() + " (phone: " + n.getPhone() + ")")
                .sorted()
                .collect(Collectors.toList());
    }


}