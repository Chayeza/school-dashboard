package co.za.school.dashboard.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import co.za.school.dashboard.domain.enumeration.YearOfStudy;

/**
 * A Coarse.
 */
@Entity
@Table(name = "coarse")
public class Coarse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "jhi_cost")
    private Integer cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_level")
    private YearOfStudy level;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Coarse courseId(Integer courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public Coarse name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public Coarse duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCost() {
        return cost;
    }

    public Coarse cost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public YearOfStudy getLevel() {
        return level;
    }

    public Coarse level(YearOfStudy level) {
        this.level = level;
        return this;
    }

    public void setLevel(YearOfStudy level) {
        this.level = level;
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
        Coarse coarse = (Coarse) o;
        if (coarse.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coarse.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Coarse{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", name='" + getName() + "'" +
            ", duration=" + getDuration() +
            ", cost=" + getCost() +
            ", level='" + getLevel() + "'" +
            "}";
    }
}
