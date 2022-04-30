package collegeFestStudentDebate.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import collegeFestStudentDebate.entity.Student;
import collegeFestStudentDebate.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/entries")
	public String getEntries(Model model) {
		List<Student> students = studentService.getStudents();
		model.addAttribute("students",students);
		return "list-entries";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student student = new Student();
		theModel.addAttribute("student",student);
		return "add-student-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") long id, Model theModel) {
		Student student = studentService.findById(id);
		theModel.addAttribute("student",student);
		return "add-student-form";
	}
	
	@RequestMapping("/save")
	public String saveStudent(@RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("department") String department, @RequestParam("country") String country) {
		
		Student student;
		
		if(id != 0) {
			student = studentService.findById(id);
			student.setName(name);
			student.setDepartment(department);
			student.setCountry(country);
		}
		else {
			student = Student.builder().name(name).department(department).country(country).build();
		}
		studentService.save(student);
		
		return "redirect:/students/entries";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") long id) {
		studentService.deleteById(id);
		return "redirect:/students/entries";
	}
	
	@RequestMapping("/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView modelAndView = new ModelAndView();

		if (user != null) {
			modelAndView.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			modelAndView.addObject("msg", 
			"You do not have permission to access this page!");
		}

		modelAndView.setViewName("403");
		return modelAndView;
	}
}
