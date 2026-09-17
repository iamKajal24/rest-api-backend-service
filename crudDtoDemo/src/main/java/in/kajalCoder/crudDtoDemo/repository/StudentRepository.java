package in.kajalCoder.crudDtoDemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.kajalCoder.crudDtoDemo.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

    Optional<Student> findByIdAndDeletedIsFalse(Long id);

    List<Student> findByDeletedIsFalse();

    boolean existsByEmail(String emailId);

   //findBy + fieldName + Condition

}
