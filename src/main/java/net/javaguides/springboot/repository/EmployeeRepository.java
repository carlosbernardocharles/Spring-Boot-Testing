package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByEmail(String email);

    //Index Param
    @Query("SELECT e FROM Employee e WHERE e.firstName = ?1 AND e.lastName = ?2")
    Employee findByJPQL(String name,String lastName);

    //Named Param
    @Query("SELECT e FROM Employee e WHERE e.firstName =:firstName AND e.lastName =:lastName")
    Employee findByJPQLNamedParam(@Param("firstName") String firstName,@Param("lastName") String lastName);

    //Native Query with Index
    @Query(value = "SELECT * FROM employees e WHERE e.first_name =?1 AND e.last_Name =?2",nativeQuery = true)
    Employee findByNativeSQL(String firstName, String lastName);

    //Native Query With named Param
    @Query(value = "SELECT * FROM employees e WHERE e.first_name =:firsName AND last_name =:lastName",nativeQuery = true)
    Employee findByNativeSQLNamed(@Param("firstName") String firstName,@Param("lastName") String lastName);


}
