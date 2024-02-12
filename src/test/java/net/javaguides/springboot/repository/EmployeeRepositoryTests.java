package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee employee;

    @BeforeEach
    public void setup(){
       employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();

    }

    //Junit test for save employee Operation
    @DisplayName("Junit test for save employee Operation")
    @Test
    public void givenEmployeeObject_WhenSave_ThenReturnSavedEmployee(){

        //given - Precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail.com")
//                .build();

        //When - action or the Behaviour that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        //Then Verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    //Junit test for get all employees operation
    @Test()
    @DisplayName("Junit test for get all employees operation ")
    public void givenEmployeeList_whenFindAll_thenEmployeesList(){
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail.com")
//                .build();

//        Employee employee1 = Employee.builder()
//                .firstName("John")
//                .lastName("Cena")
//                .email("cena@gmail.com")
//                .build();

        employeeRepository.save(employee);
        //employeeRepository.save(employee1);

        //When - action or behaviour that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        //then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).size().isEqualTo(2);
    }

    //Junit test for get employee by id operation
    @DisplayName("Junit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmploYeeObject(){
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //When - action or behaviour that we are going to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        //then - verify the output
        assertThat(employeeDB).isNotNull();

    }

    //Junit test for get employee by email operation
    @DisplayName("Junit test for get employee by email operation")
    @Test
    public void givenEmployee_whenFindByEmail_thenReturnEmployeeObject(){
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //When - action or behaviour that we are going to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        //then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    //Junit test for update employee operation
    @DisplayName("Junit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail.com")
//                .build();
        employeeRepository.save(employee);
        //When - action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("ram@gmail.com");
        savedEmployee.setFirstName("Ram");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        //then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("ram@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ram");
    }

    //Junit test for delete Employee operation
    @DisplayName("Junit test for delete Employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee(){
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail.com")
//                .build();

        //When - action or behaviour that we are going to test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeById = employeeRepository.findById(employee.getId());

        //then - verify the output

        assertThat(employeeById).isEmpty();
    }
    //Junit test for custom Query using JPQL with index
    @DisplayName("Junit test for custom Query using JPQL with index")
    @Test
    public void givenFisrtNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail.com")
//                .build();
        employeeRepository.save(employee);
        //When - action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQL(employee.getFirstName(), employee.getLastName());
        //then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    //Junit test for custom Query using JPQL with Named Param
    @DisplayName("Junit test for custom Query using JPQL with Named Param")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParam_thenReturnEmployeeObject(){
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail.com")
//                .build();
        employeeRepository.save(employee);
        //When - action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParam(employee.getFirstName(), employee.getLastName());
        //then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    //Junit test for custom Query using JPQL with Index Param (Native SQL)
    @DisplayName("Junit test for custom Query using JPQL with Index Param (Native SQL)")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail.com")
//                .build();
        employeeRepository.save(employee);
        //When - action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());
        //then - verify the output
        assertThat(savedEmployee).isNotNull();
    }


    //Junit test for custom Query using JPQL with Index Param (Native SQL)
    @DisplayName("Junit test for custom Query using JPQL with Named Param (Native SQL)")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamed_thenReturnEmployeeObject(){
        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail.com")
//                .build();
        employeeRepository.save(employee);
        //When - action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQLNamed(employee.getFirstName(), employee.getLastName());
        //then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

}
