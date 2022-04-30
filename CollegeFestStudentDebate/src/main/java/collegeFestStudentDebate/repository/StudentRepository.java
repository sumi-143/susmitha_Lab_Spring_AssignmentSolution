package collegeFestStudentDebate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import collegeFestStudentDebate.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
}
