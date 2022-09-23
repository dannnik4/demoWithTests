package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.*;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.util.config.EmployeeConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Employee", description = "Employee API")
public class Controller {

    private final Service service;
    private final EmployeeConverter converter;

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

    public EmployeeSaveDto saveEmployee(@RequestBody @Valid EmployeeSaveDto requestForSave) {

        var employee = converter.getMapperFacade().map(requestForSave, Employee.class);

        var dto = converter.toSaveDto(service.create(employee));

        return dto;

    }

    @PostMapping("/users/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new employee.", description =
            "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})

    public EmployeeSave2Dto saveEmployee2(@RequestBody @Valid EmployeeSave2Dto requestForSave) {

        Employee employee = converter.getMapperFacade().map(requestForSave, Employee.class);
        EmployeeSaveDto dto = converter.toSaveDto(service.create(employee));

        return dto;

        @Operation(summary = "This is endpoint to get list of all employees.", description =
                "Create request to find all employees.", tags = {"Employee"})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
                @ApiResponse(responseCode = "400", description = "Invalid input"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
                @ApiResponse(responseCode = "409", description = "Employee already exists")})
        @GetMapping("/users")
        @ResponseStatus(HttpStatus.OK)
        public List<Employee> getAllUsers () {
            return service.getAll();
        }

        @GetMapping("/users/{id}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "This is endpoint returned employee by his id.", description =
                "Create request to read a employee by id", tags = {"Employee"})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
                @ApiResponse(responseCode = "400", description = "Invalid input"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
                @ApiResponse(responseCode = "409", description = "Employee already exists")})
        public EmployeeReadDto getEmployeeById (@PathVariable Integer id){
            log.debug("getEmployeeById() Controller - start: id = {}", id);
            var employee = service.getById(id);
            log.debug("getById() Controller - to dto start: id = {}", id);
            var dto = converter.toReadDto(employee);
            log.debug("getEmployeeById() Controller - end: name = {}", dto.name);
            return dto;
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

        public EmployeeRead2Dto getEmployeeById2 (@PathVariable Integer id){
            log.debug("getEmployeeById() Controller - start: id = {}", id);
            var employee = service.getById(id);
            log.debug("getById() Controller - to dto start: id = {}", id);
            var dto = converter.toRead2Dto(employee);
            log.debug("getEmployeeById() Controller - end: name = {}", dto.name);
            return dto;

        }

        @PatchMapping ("/users/update/{id}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "This is endpoint returned a employee by his id.", description =
                "Create request to read a employee by id", tags = {"Employee"})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
                @ApiResponse(responseCode = "400", description = "Invalid input"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
                @ApiResponse(responseCode = "409", description = "Employee already exists")})

        public EmployeeUpdateDto refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
            EmployeeUpdateDto dto = converter.toUpdateDto(service.updateById(id, employee));
            return dto;
        }

        @PatchMapping ("/users/update2/{id}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "This is endpoint returned a employee by his id.", description =
                "Create request to read a employee by id", tags = {"Employee"})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
                @ApiResponse(responseCode = "400", description = "Invalid input"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
                @ApiResponse(responseCode = "409", description = "Employee already exists")})
        public EmployeeUpdate2Dto refreshEmployee2(@PathVariable("id") Integer id, @RequestBody Employee employee) {
            EmployeeUpdate2Dto dto = converter.toUpdate2Dto(service.updateById(id, employee));
            return dto;
        }

        @PatchMapping("/users/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @Operation(summary = "This is endpoint deleted employee by his id.", description =
                "Create request to read a employee by id", tags = {"Employee"})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
                @ApiResponse(responseCode = "400", description = "Invalid input"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
                @ApiResponse(responseCode = "409", description = "Employee already exists")})
        public void removeEmployeeById (@PathVariable Integer id){
            service.removeById(id);
        }

        @DeleteMapping("/users")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @Operation(summary = "This is endpoint deleted all employees.", description =
                "Create request to read a employee by id", tags = {"Employee"})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
                @ApiResponse(responseCode = "400", description = "Invalid input"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
                @ApiResponse(responseCode = "409", description = "Employee already exists")})
        public void removeAllUsers () {
            service.removeAll();
        }


        @GetMapping(value = "/users", params = {"name"})
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "This is endpoint to get all employees by name.", description =
                "Create request to get employees by name", tags = {"Employee"})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
                @ApiResponse(responseCode = "400", description = "Invalid input"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
                @ApiResponse(responseCode = "409", description = "Employee already exists")})
        public List<Employee> getName (@RequestParam(value = "name") String name){
            return service.getName(name);
        }

        @GetMapping(value = "/users", params = {"country"})
        @ResponseStatus(HttpStatus.OK)
        public List<Employee> getCountry (@RequestParam(value = "country") String country){
            return service.getCountry(country);
        }

        @GetMapping(value = "/users", params = {"allname"})
        @ResponseStatus(HttpStatus.OK)
        public List<Employee> getAllName (@RequestParam(value = "name") String name){
            return service.getAllName(name);
        }

        @PutMapping("/users/phone/{phone}")
        @ResponseStatus(HttpStatus.OK)
        public List<Employee> getNameByPhone (@RequestParam(value = "phone") Integer phone){
            return service.getNameByPhone(phone);
        }

    }
}