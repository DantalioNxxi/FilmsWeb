
package ncec.cfweb;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author DantalioNxxi
 */
@Entity
@IdClass(Person.PersonPk.class)
public class Person {

    public static class PersonPk implements Serializable {
        
        static final long serialVersionUID = 1L;

        String firstname;
        String lastname;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PersonPk personPk = (PersonPk) o;
            return Objects.equals(firstname, personPk.firstname) &&
                    Objects.equals(lastname, personPk.lastname);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstname, lastname);
        }
        
    }
    
////    @Id
////    @Column
////    @GeneratedValue(strategy=GenerationType.AUTO)
    @Transient
    private Long id;

    @Id
    private String firstname;

    @Id
    private String lastname;

    private int age;

    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Filmrole> personages;
    
    @ManyToMany(mappedBy = "persons", fetch = FetchType.LAZY)
    private Set<Movie> movies;

    public Person() {
    }

    public Person(Gender gender, String firstname, String lastname, int age) {
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public Person(int age, String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.firstname);
        hash = 67 * hash + Objects.hashCode(this.lastname);
        hash = 67 * hash + this.age;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (this.gender != other.gender) {
            return false;
        }
        return true;
    }

}
