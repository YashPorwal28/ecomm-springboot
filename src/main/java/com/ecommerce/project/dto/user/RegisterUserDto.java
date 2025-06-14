package com.ecommerce.project.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

        private String email;

        private String password;

        private String fullName;

        private String user_role;



}
