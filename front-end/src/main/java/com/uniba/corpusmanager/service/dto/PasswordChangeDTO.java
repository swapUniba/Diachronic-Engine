package com.uniba.corpusmanager.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO representing a password change required data - current and new password.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasswordChangeDTO {

    private String currentPassword;

    private String newPassword;
}
