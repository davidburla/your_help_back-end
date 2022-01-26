package com.your_help.example.model.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDto
{
    private Integer personId;
    private String name;
    private String prename;
    private String email;
    private String password;
    private String phone;
    private Boolean isDeleted;

    @Override
    public String toString() {
        return "PersonDto{" +
                "id=" + personId +
                ", name='" + name + '\'' +
                ", prename='" + prename + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", isDeleted='"+ isDeleted + "\'" +
                '}';
    }
}
