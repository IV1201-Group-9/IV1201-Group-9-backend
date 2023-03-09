package com.iv1201.recapp.Models.RecruiterDTOs;

/**
 * Outging DTO of each of the applications information to be handled by recruiters.
 */
public class SingleUserApplicationDTO {
    Long id;
    String firstname;
    String surname;
    int age;
    String status;

    public SingleUserApplicationDTO() {
    }

    public SingleUserApplicationDTO(Long id,
                                    String firstname,
                                    String surname,
                                    int age,
                                    String status) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SingleUserApplicationDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", status='" + status + '\'' +
                '}';
    }
}
