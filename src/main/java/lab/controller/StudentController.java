package lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import entity.Student;
import repository.StudentRepository;

@CrossOrigin
@RestController
public class StudentController {
	@Autowired
	StudentRepository studentRepository;
	
	@RequestMapping(value="/submitStudentDetails",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.POST
			)
	public void submitStudentDetails(@RequestBody Student student) {
		//System.out.println(student);
		studentRepository.save(student);
	}
	
	@RequestMapping(value="/findStudentById",
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.GET
			)
	@ResponseBody
	private ResponseEntity<Student> findStudent(String email){
		Student student = studentRepository.findOne(email);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/login",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.POST
			)
	@ResponseBody
	private ResponseEntity<?> login(@RequestBody Student student) {
		Student test = studentRepository.findOne(student.getEmail());
		if(!test.getPassword().equals(student.getPassword())) {
			return null;
		} else {
			return new ResponseEntity<> (test, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/findList",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.POST
			)
	@ResponseBody
	private ResponseEntity<?> findList() {
		List<Student> students = studentRepository.findAll();
		return new ResponseEntity<> (students, HttpStatus.OK);
	}

}
