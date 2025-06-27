package com.km.cruddemo;

import com.km.cruddemo.dao.StudentDAO;
import com.km.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDao) {
		return runner -> {
			// This is where you can add any startup logic if needed
			System.out.println("Cruddemo Application has started successfully!");
//			createStudent(studentDao);
			createMultipleStudents(studentDao);
//			readStudent(studentDao);
//			queryForStudents(studentDao);
//			queryForStudentsByLastName(studentDao);
//			updateStudent(studentDao);
//			deleteStudent(studentDao);
//			deleteAllStudents(studentDao);
		};

	}

	private void deleteAllStudents(StudentDAO studentDao) {
		System.out.println("Deleting all students");
		int numRowsDeleted = studentDao.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDao) {
		int studentId = 3;
		System.out.println("Deleting student " + studentId);
		studentDao.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDao) {
		// retrieve student based on id: Primary key
		int studentId = 1;
		System.out.print("Getting student with id: " + studentId);
		Student myStudent = studentDao.findById(studentId);

		// change the first name to Scooby
		System.out.println("Updating student ... ");
		myStudent.setFirstName("John");

		// update the student
		studentDao.update(myStudent);

		// display the updated student
		Student newStudent = studentDao.findById(studentId);
		System.out.println("Updated student: " + newStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDao) {
		// get a list of students
		List<Student> students = studentDao.findByLastName("Moo");

		// display list of students
		for (Student tempStudent : students) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDao) {
		// get a list of students
		List<Student> students = studentDao.findAll();

		// display a list of students
		for (Student tempStudent : students) {
			System.out.println(tempStudent);
		}

	}

	private void readStudent(StudentDAO studentDao) {
		// create a student
		System.out.println("Creating a new student object...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@gmail.com");

		// save student
		System.out.println("Saving the student...");
		studentDao.save(tempStudent);

		// display id of save student
		int theID = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theID);

		// retrieve student based on id: primary key
		System.out.println("\nRetrieving student with id: " + theID);
		Student myStudent = studentDao.findById(theID);

		// display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDao) {
		// create multiple students
		System.out.println("Creating 3 student objects...");
		Student tempStudent1 = new Student("Mary", "Moo", "mary@gmail.com");
		Student tempStudent2 = new Student("Anita", "Mee", "anita@gmail.com");
		Student tempStudent3 = new Student("Lan", "Maa", "lan@gmail.com");

		// save the student objects
		System.out.println("Saving the students...");
		studentDao.save(tempStudent1);
		studentDao.save(tempStudent2);
		studentDao.save(tempStudent3);

		// display ids of the saved students
		System.out.println("Saved students. Generated ids: " +
			tempStudent1.getId() + ", " +
			tempStudent2.getId() + ", " +
			tempStudent3.getId());
	}

	private void createStudent(StudentDAO studentDao) {
		// create the student object
		System.out.println("Creating a new student object...");
		Student tempStudent = new Student("John", "Doe", "ken@gmail.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDao.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}

}
