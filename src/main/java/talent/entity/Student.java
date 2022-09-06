package talent.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import talent.enums.StudyFormat;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class    Student {
    @Id
    @SequenceGenerator(name = "students_sequence", sequenceName = "students_sequence", allocationSize = 1)
    @GeneratedValue(generator = "students_sequence", strategy = GenerationType.SEQUENCE)
    private Long studentId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "e_mail", unique = true)
    private String email;
    @Column(name = "study_format")
    private StudyFormat studyFormat;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Course course;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Company company;

    public Student(String firstName, String lastName, String phoneNumber, String email, StudyFormat studyFormat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }

}