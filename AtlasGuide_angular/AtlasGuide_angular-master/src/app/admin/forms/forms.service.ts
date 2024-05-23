import { Injectable } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {passwordValidator} from "../authentification/passwordValidator/password-validator";
import {priceRangeValidator} from "../products/price-range.directive";
import {City} from "../../../model/enumeration/City.enum";

@Injectable({
  providedIn: 'root'
})
export class FormsService {
  loginForm: FormGroup;
  userForm: FormGroup;
  activityForm: FormGroup;
  passwordConfirmation: FormGroup;
  programelementform: FormGroup;
  resetPasswordForm: FormGroup;
  constructor(private formBuilder: FormBuilder) {
    // logging form //
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    },);
    // registration form //
    this.userForm = this.formBuilder.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required,passwordValidator]],
      phone: ['', Validators.required],
    });
    this.passwordConfirmation = this.formBuilder.group({
      passwordConfirmation: ['',[Validators.required,passwordValidator]],
    })
    // activity form //
    this.activityForm = new FormGroup({
      designation: new FormControl('', {
        nonNullable: true,
        validators: Validators.required
      }),
      description: new FormControl('', {
        nonNullable: true,
        validators: Validators.required
      }),
      descriptionDetail: new FormControl('', {
        nonNullable: true,
        validators: Validators.required
      }),
      price: new FormControl<number | undefined>(undefined, {
        nonNullable: true,
        validators: [Validators.required, priceRangeValidator()]
      }),
      category: new FormControl<string | 'GGF'>('VHG', {
        nonNullable: true,
        validators: Validators.required
      }),
      imageUrl: new FormControl('', {
        nonNullable: true,

      }),
      startDate: new FormControl<Date | any>(undefined, {
        nonNullable: true,
      }),
      endDate: new FormControl<Date | any>(undefined, {
        nonNullable: true,
      }),
      bookingEndDate: new FormControl<Date | any>(undefined, {
        nonNullable: true,
      }),
      bookingStartDate: new FormControl<Date | any>(undefined, {
        nonNullable: true,
      }),
      city: new FormControl<string | City>('', {
        nonNullable: true,
      }),
      maxParticipants: new FormControl<number | any>(undefined, {
        nonNullable: true,
      }),
      participants: new FormControl<number | any>(undefined, {
        nonNullable: true,
      }),
      isAvailableYearRound: new FormControl<boolean | any>(false, {
        nonNullable: true,
      }),
      isFlexibleDates: new FormControl<boolean | any>(true, {
        nonNullable: true,
      }),
      isPlacesLimited: new FormControl<boolean | any>(false, {
        nonNullable: true,
      }),
    });

    this.programelementform = this.formBuilder.group(
      {
        name: ['', Validators.required],
        dscription: ['', Validators.required],
      }
    )

    this.resetPasswordForm = this.formBuilder.group(
      {
        email: ['', [Validators.required, Validators.email]],
        newPassword: ['', [Validators.required, passwordValidator]],
      }
    )
  }
}
