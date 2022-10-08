package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.*;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.util.config.mapStrukt.EmployeeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Employee", description = "Employee API")
public class Controller {

    private final Service service;
    private final EmployeeMapper employeeMapper;


    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new employee.", description =
            "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public Employee saveEmployee(@RequestBody Employee employee) {
        return service.create(employee);
    }


    //save 2 dto
    @PostMapping("/users/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new employee.", description =
            "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public EmployeeSaveDto saveEmployee2(@RequestBody Employee employee) {
        return employeeMapper.employeeSave2Dto(service.create(employee));
    }


    //Получение списка юзеров
    @Operation(summary = "This is endpoint to get list of all employees.", description =
            "Create request to find all employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return service.getAll();
    }


    //Получение списка юзеров по 38
    @GetMapping("/users/phones/{phone}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint to find employee by phone code.", description =
            "Create request to find employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public List<Employee> getClientsByCodes(@PathVariable("phone") String phone) {
        return service.getEmployeeByPhoneU(phone);
    }


    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint returned employee by his id.", description =
            "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public Employee getEmployeeById(@PathVariable Integer id) {
        return service.getById(id);
    }


    @GetMapping("/users/dto/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint returned a employee by his id.", description =
            "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public EmployeeReadDto getEmployeeById2Dto(@PathVariable Integer id) {
        return employeeMapper.employeeReadDto(service.getById(id));
    }

    //Обновление юзера
    @PatchMapping("/users/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint returned a employee by his id.", description =
            "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public EmployeeUpdateDto updateDto(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return employeeMapper.employeeUpdateDto(service.updateById(id, employee));
    }


    //Обновление юзера2
    @PatchMapping("/users/update2/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint returned a employee by his id.", description =
            "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public EmployeeUpdateDto update2Dto(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return employeeMapper.employeeUpdateDto(service.updateById(id, employee));
    }


    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "This is endpoint deleted employee by his id.", description =
            "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public void removeEmployeeById(@PathVariable Integer id) {
        service.removeById(id);
    }


    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "This is endpoint deleted all employees.", description =
            "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public void removeAllUsers() {
        service.removeAll();
    }


    // выводит всех пользователей с указанным именем
    @GetMapping(value = "/users", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint to get all employees by name.", description =
            "Create request to get employees by name", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public List<Employee> getName(@RequestParam(value = "name") String name) {
        return service.getName(name);
    }


    // выводит всех пользователей по указанной стране
    @GetMapping(value = "/users", params = {"country"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getCountry(@RequestParam(value = "country") String country) {
        return service.getCountry(country);
    }


    // выводит всех пользователей из колонки "name"
    @GetMapping(value = "/users", params = {"allname"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllName(@RequestParam(value = "name") String name) {
        return service.getAllName(name);
    }


    //получение пользователя по его номеру телефона
    @GetMapping(value = "/users", params = {"phone"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getNameByPhone(@RequestParam(value = "phone") String phone) {
        return service.getNameByPhone(phone);
    }

    @GetMapping("/users/name")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint Find all employees with the given name, " +
            "and return them in a pageable format.", description =
            "Create request to find all employee by name", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public Page<Employee> findByName(@RequestParam(required = false) String name,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "") List<String> sortList,
                                     @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) {
        return service.findByName(name, page, size, sortList, sortOrder.toString());
    }

    @GetMapping("/users/address")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint Find all employees with the given address, " +
            "and return them in a pageable format.", description =
            "Create request to find all employee by address", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public Page<Employee> findByAddress(@RequestParam(required = false) String address,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "3") int size,
                                        @RequestParam(defaultValue = "") List<String> sortList,
                                        @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) {
        return service.findByAddress(address, page, size, sortList, sortOrder.toString());
    }

    @GetMapping("/users/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint Find all employees, sort them by the given list of fields, and return " +
            "the page of results.", description =
            "Create request to read all employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public Page<Employee> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "") List<String> sortList,
                                  @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {
        return service.findAll(page, size, sortList, sortOrder.toString());
    }

    @GetMapping("/users/countries/different")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint Search for different countries in the database" +
            "and return a list of all different countries in the database.", description =
            "Create request to read all employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public List<String> getAllDifferentCountries() {
        return service.findDifferentCountries();
    }

    @GetMapping("/users/phones/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint Find all users phones numbers" +
            "and return a list of all the phone numbers in the database.", description =
            "Create request to read all employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public List<String> getAllUsersPhones() {
        return service.findAllPhone();
    }

    @GetMapping("/users/names/short")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint Find all the short names in the database" +
            "and return a list of short names", description =
            "Create request to read all employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public List<String> getAllShortNames() {
        return service.findShortNames();
    }

    @GetMapping("/users/np")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint Find all users phones numbers" +
            "and return a list of all the phone numbers in the database.", description =
            "Create request to read all employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public List<String> getNamesAndPhones() {
        return service.findPhoneAndName();
    }

}