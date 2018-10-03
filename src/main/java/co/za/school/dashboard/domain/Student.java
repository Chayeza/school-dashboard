package co.za.school.dashboard.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import co.za.school.dashboard.domain.enumeration.Sex;

import co.za.school.dashboard.domain.enumeration.Race;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @NotNull
    @Column(name = "id_number", nullable = false)
    private Integer idNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "race", nullable = false)
    private Race race;

    @NotNull
    @Column(name = "nationality", nullable = false)
    private String nationality;

    @NotNull
    @Column(name = "contact", nullable = false)
    private Integer contact;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Student studentId(Integer studentId) {
        this.studentId = studentId;
        return this;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public Student name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public Student surname(String surname) {
        this.surname = surname;
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public Student sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public Student idNumber(Integer idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    public Race getRace() {
        return race;
    }

    public Student race(Race race) {
        this.race = race;
        return this;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getNationality() {
        return nationality;
    }

    public Student nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getContact() {
        return contact;
    }

    public Student contact(Integer contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        if (student.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", studentId=" + getStudentId() +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", sex='" + getSex() + "'" +
            ", idNumber=" + getIdNumber() +
            ", race='" + getRace() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", contact=" + getContact() +
            "}";
    }
}
