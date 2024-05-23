import {AbstractControl, ValidationErrors} from "@angular/forms";

export const passwordValidator = (control: AbstractControl): ValidationErrors | null => {
  const value: string = control.value;

  // Check if the password contains at least one uppercase letter
  const uppercaseRegex: RegExp = /^(?=.*[A-Z])/;

  // Check if the password contains at least one lowercase letter
  const lowercaseRegex: RegExp = /^(?=.*[a-z])/;

  // Check if the password contains at least one numeric digit
  const digitRegex: RegExp = /^(?=.*\d)/;

  // Check if the password contains at least one special character
  const specialCharRegex: RegExp = /^(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?])/;

  // Validate the password
  const isValid: boolean = uppercaseRegex.test(value) &&
    lowercaseRegex.test(value) &&
    digitRegex.test(value) &&
    specialCharRegex.test(value) &&
    value.length >= 8 &&
    value.length <= 16;

  // Return error message for minimum length not met
  if (value.length < 8) {
    return { minLengthNotMet: true };
  }

  // Return error message for maximum length exceeded
  if (value.length > 16) {
    return { maxLengthExceeded: true };
  }

  // Return error message if any other condition is not met
  if (!isValid) {
    return { passwordInvalid: true };
  }

  return null;
}
