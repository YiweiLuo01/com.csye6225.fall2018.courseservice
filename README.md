# com.csye6225.fall2018.courseservice


EB address:http://comcsye6225fall2018courseservic-env.5ppdmdmcva.us-east-2.elasticbeanstalk.com



For studenServiceTest:

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Student;

public class studentAddingTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Student s = new Student();
		s.setProgramId(20182018);
		
		s.setFirstName("Yiwei");
		s.setLastName("Luo");
		s.setImage("");
		s.setCourseSet(new HashSet<String>());
		new StudentService().studentUpdating("11111",s);
	}

}
--------------------------------------------------------------------
import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import com.csye6225.fall2018.courseservice.datamodel.Course;

public class CourseAddingTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Course course = new Course();
		course.setCourseId(20172017) ;
		course.setLectureId(1111111);
    course.setBoard("");
    course.setRoster("");
    course.setStudentId() = new Long[]{111111,22222222,44444444};
    course.setProfessorId(198402212);
    course.setStudentTA(199201212) ;
		
		new CourseService().courseAdding(course);
	}

}
------------------------------------------------------------------
public class ProfessorAddingTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Professor prof = new Professor();
		course.setProfessorId(20172017) ;
		course.setFirstName("Ji");
    course.setLastName("A");
    course.setDepartment("Engineering");
    course.setProfessorId(1112222222);
    course.setJoiningDate(19840212);
		
		new CourseService().courseAdding(prof);
	}

}



