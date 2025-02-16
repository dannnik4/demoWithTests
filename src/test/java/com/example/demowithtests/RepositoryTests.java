package com.example.demowithtests;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositoryTests {

    @Autowired
    private Repository repository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest() {

        Employee employee = Employee.builder().name("Mark").country("England").build();

        repository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployeeTest() {

        Employee employee = repository.findById(1).orElseThrow();

        Assertions.assertThat(employee.getId()).isEqualTo(1);

    }

    @Test
    @Order(3)
    public void getListOfEmployeeTest() {

        Employee employee = Employee.builder().name("Mark").build();

        repository.save(employee);

        List<Employee> employeesList = repository.findAll();

        Assertions.assertThat(employeesList.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest() {

        Employee employee = repository.findById(1).get();

        employee.setName("Martin");
        Employee employeeUpdated = repository.save(employee);

        Assertions.assertThat(employeeUpdated.getName()).isEqualTo("Martin");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest() {

        Employee employee = repository.findById(1).get();

        repository.delete(employee);

        //repository.deleteById(1L);

        Employee employee1 = null;

        Optional<Employee> optionalAuthor = Optional.ofNullable(repository.findByName("Martin"));

        if (optionalAuthor.isPresent()) {
            employee1 = optionalAuthor.get();
        }

        Assertions.assertThat(employee1).isNull();
    }

    @Test
    public void getEmployeeByNameTest() {
        Employee employee = Employee.builder().name("Bob").build();
        repository.save(employee);
        List<Employee> employeesList = repository.findAll();
        Assertions.assertThat(employeesList.get(0).getName()).isEqualTo("Bob");
    }

    @Test
    public void getEmployeeByPhoneTest() {
        Employee employee = Employee.builder().phone("123456").build();
        repository.save(employee);
        List<Employee> employeesList = repository.findAll();
        Assert.assertEquals(employeesList.get(0).getPhone(),"123456",0.1);
        Assert.assertEquals(employeesList.get(0).getPhone(),123456,0.1);
    }

    @Test
    public void getEmployeeByCountryTest() {
        Employee employee = new Employee();
        employee.setCountry("uk");
        repository.save(employee);
        List<Employee> employeesList = repository.findAll();
        Assertions.assertThat(employeesList.get(0).getCountry()).isEqualTo("uk");
    }

    @Test
    public void getAllByNameTest() {
        Employee employee = Employee.builder().name("Bob").build();
        repository.save(employee);
        List<Employee> employeesList = repository.findAll();
        Assertions.assertThat(employeesList.get(0).getName()).isEqualTo("Bob");

    }

}