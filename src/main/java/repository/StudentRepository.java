package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
	@Query("select Student s from student where s.firstName =:firstName")
	public List<Student> findStudentByFirstName(String firstName);
}
