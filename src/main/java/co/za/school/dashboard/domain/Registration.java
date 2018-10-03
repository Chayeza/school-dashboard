package co.za.school.dashboard.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Registration.
 */
@Entity
@Table(name = "registration")
public class Registration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @NotNull
    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "credits")
    private Integer credits;

    @Column(name = "jhi_date")
    private ZonedDateTime date;

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

    public Registration studentId(Integer studentId) {
        this.studentId = studentId;
        return this;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Registration courseId(Integer courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCredits() {
        return credits;
    }

    public Registration credits(Integer credits) {
        this.credits = credits;
        return this;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Registration date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
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
        Registration registration = (Registration) o;
        if (registration.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), registration.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Registration{" +
            "id=" + getId() +
            ", studentId=" + getStudentId() +
            ", courseId=" + getCourseId() +
            ", credits=" + getCredits() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
