package collegeFestStudentDebate.service;

import java.util.List;

import collegeFestStudentDebate.entity.Student;

public interface StudentService {

	List<Student> getStudents();

	Student findById(long id);

	void save(Student student);

	void deleteById(long id);

}