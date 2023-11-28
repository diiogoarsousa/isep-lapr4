package ecourse.dto;

import eapli.framework.representations.dto.DTO;

@DTO
public class StudentDto {
    public String username;

    public String password;

    public String name;

    public String email;

    public String createdOn;

    public String mecanographicNumber;

    public String birthDate;

    public String taxNumber;

    public StudentDto(String username, String password, String name, String email, String createdOn, String mecanographicNumber, String birthDate, String taxNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdOn = createdOn;
        this.mecanographicNumber = mecanographicNumber;
        this.birthDate = birthDate;
        this.taxNumber = taxNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getMecanographicNumber() {
        return mecanographicNumber;
    }

    public void setMecanographicNumber(String mecanographicNumber) {
        this.mecanographicNumber = mecanographicNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }
}
