package co.za.school.dashboard.school.student.record;

import co.za.school.dashboard.domain.Student;
import co.za.school.dashboard.repository.RegistrationRepository;
import co.za.school.dashboard.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AcademicRecord {

    private Long studentId;
    private String studentName;
    private String lastName;
    private int age;
    private Long idNumber;

    //Autowire student repository:
    @Autowired
    private StudentRepository studentRepository;

    //Autowire registration repository:
    @Autowired
    private RegistrationRepository registrationRepository;

    //This is the student we are creating the academic record for;
    private Student student;

    //This is the list of registration for the student:
    private List<co.za.school.dashboard.domain.Registration> registration;

    public AcademicRecord(Long studentId) {
        this.studentId = studentId;
        this.student=studentRepository.getOne(this.studentId);
        this.registration = registrationRepository.findAllForStudent(this.studentId);
        this.studentName = this.student.getName();
        this.lastName = this.student.getSurname();
        this.idNumber = Long.parseLong(this.student.getIdNumber().toString());

        this.age = computeAge(this.idNumber);

    }

    private int computeAge(Long idNumber) {
        // Compute the age of the student:

        // Convert the  integer number to String so that we will be able to get individual characters:
        // String numberString = Integer.toString(idNumber);
        String numberString = Long.toString(idNumber);
        // Create a character array with the all the numbers from the string
        // This is so that we have each individual character in an array index:
        char[] numberChars = numberString.toCharArray();

        // Get the first pair of numbers:
        String year = String.valueOf(numberChars[0])   + String.valueOf(numberChars[1]);
        int intYear = Integer.parseInt(year);

        String studentYear = "";

        // Note: We are assuming that every year below or equal 50 is from the 20th century
        //       else the year is from the 19th century
        //       we are disregarding anything from before
        if (intYear <= 50) {
            // the year is less than or equal to 50
            // we set the student year to a year on the 20th century
            studentYear = 20 + year;
        } else {
            // The year is greater than 50 then we assume its a year on the 19th century
            studentYear = 19 + year;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        String currentYear = dtf.format(now);
        int studentAge = Integer.parseInt(currentYear) - Integer.parseInt(studentYear);

        return studentAge;
    }


    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public Student getStudent() {
        return student;
    }

    public List<co.za.school.dashboard.domain.Registration> getRegistration() {
        return registration;
    }
}
