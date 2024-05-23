import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AccountComponent} from './account/account.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from "@angular/material/autocomplete";
import {MatButton} from "@angular/material/button";
import {MatCheckbox} from "@angular/material/checkbox";
import {
  MatDatepickerToggle,
  MatDateRangeInput,
  MatDateRangePicker,
  MatEndDate,
  MatStartDate
} from "@angular/material/datepicker";
import {MatError, MatFormField, MatHint, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatStep, MatStepLabel, MatStepper, MatStepperNext, MatStepperPrevious} from "@angular/material/stepper";


@NgModule({
  declarations: [
    AccountComponent
  ],
  exports: [
    AccountComponent
  ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MatAutocomplete,
        MatAutocompleteTrigger,
        MatButton,
        MatCheckbox,
        MatDateRangeInput,
        MatDateRangePicker,
        MatDatepickerToggle,
        MatEndDate,
        MatError,
        MatFormField,
        MatHint,
        MatInput,
        MatLabel,
        MatOption,
        MatStartDate,
        MatStep,
        MatStepLabel,
        MatStepper,
        MatStepperNext,
        MatStepperPrevious,
        MatSuffix,
   ]
})
export class AccountModule { }
