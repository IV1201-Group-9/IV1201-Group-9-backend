package com.iv1201.recapp.Models.RecruiterDTOs;

public class SingleUserApplicationDTO {
    Long id;
    String firstname;
    String surname;
    int age;
    String applicationStatus;

    public SingleUserApplicationDTO() {
    }

    public SingleUserApplicationDTO(Long id,
                                    String firstname,
                                    String surname,
                                    int age,
                                    String applicationStatus) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
        this.applicationStatus = applicationStatus;
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

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    @Override
    public String toString() {
        return "SingleUserApplicationDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", age='" + age + '\'' +
                ", applicationStatus='" + applicationStatus + '\'' +
                '}';
    }
}
