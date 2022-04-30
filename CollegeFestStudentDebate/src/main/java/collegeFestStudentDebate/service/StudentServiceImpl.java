package collegeFestStudentDebate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import collegeFestStudentDebate.entity.Student;
import collegeFestStudentDebate.repository.StudentRepository;

@Service
@EnableTransactionManagement
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	@Transactional
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	@Transactional
	public Student findById(long id) {
		Optional<Student> result = studentRepository.findById(id);
		
		Student student = null;

		if (result.isPresent()) {
			student = result.get();
		} else {
			// we didn't find the student
			throw new RuntimeException("Did not find student id - " + id);
		}

		return student;
	}

	@Override
	@Transactional
	public void save(Student student) {
		studentRepository.saveAndFlush(student);
	}

	@Override
	@Transactional
	public void deleteById(long id) {
		Optional<Student> result = studentRepository.findById(id);

		if (result.isPresent()) {
			studentRepository.deleteById(id);
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find student record to delete with id - " + id);
		}
	}
}
