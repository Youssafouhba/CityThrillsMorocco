import {Component, EventEmitter, Inject, Input, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Activity} from "../../../models/Activity";
import {ActivityService} from "../activity.service";
import {map, Observable} from "rxjs";
import {AuthentificationService} from "../../authentification/authentification.service";
import {FormsService} from "../../forms/forms.service";
import {ActivityCategories} from "../../enumerations/activity-categories";
import {City} from "../../../../model/enumeration/City.enum";
import {SafeResourceUrl} from "@angular/platform-browser";
import {GoogleMapsService} from "../../../maps/google-maps.service";
import {FormGroup} from "@angular/forms";
import {AlertsService} from "../../alerts/alerts.service";
import {Route, Router} from "@angular/router";


@Component({
  selector: 'app-activity-update',
  templateUrl: './activity-update.component.html',
  styleUrls: ['./activity-update.component.css']
})
export class ActivityUpdateComponent {
  isLinear = false;
  @Output() added = new EventEmitter<Activity>();

  cities: City[] = Object.values(City);
  filteredCities!: Observable<City[]> | undefined;

  filename = "";
  unsafeUrl = 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d14131401.081666416!2d-27.633198336256214!3d30.150012426714188!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd0b88619651c58d%3A0xd9d39381c42cffc3!2sMaroc!5e0!3m2!1sfr!2sma!4v1715315794985!5m2!1sfr!2sma';
  safeUrl: SafeResourceUrl = this.googleMapService.sanitizer.bypassSecurityTrustResourceUrl(this.unsafeUrl);

  activity$: Observable<Activity> | undefined;
  activityForm!: FormGroup;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private activityService: ActivityService,
    private AuthenticationService: AuthentificationService,
    private form: FormsService,
    private googleMapService: GoogleMapsService,
    private rout: Router,
    private alert: AlertsService,
  ) { }




  activities: Activity[] = [];
  activities$: Observable<Activity[]> | undefined;
  categories = Object.values(ActivityCategories);
  showPriceRangeHint = false;
  err: string[] = [];

  currentFile?: File;
  progress = 0;
  message = '';
  fileName = 'Select File';
  selectFile(event: any): void {
    if (event.target.files && event.target.files[0]) {
      const file: File = event.target.files[0];
      this.currentFile = file;
      this.fileName = this.currentFile.name;
    } else {
      this.fileName = 'Select File';
    }
  }
  ngOnInit(): void {
    this.activityForm = this.form.activityForm;
    this.AuthenticationService.isAuthenticated()
    this.activity$ = this.activityService.getActivity(this.data.id)
    this.activity$.subscribe(activity => {
      this.activityForm.patchValue(activity);
      this.filename = activity.imageUrl;
      console.log(this.filename)
    });


    this.activityForm.get('price')?.valueChanges.subscribe(price => {
      if (price) {
        this.showPriceRangeHint = price > 1 && price < 10000;
      }
    });

    this.activities$ = this.activityForm.get('designation)')?.valueChanges.pipe(
      map(designation => this.activities.filter(activity => activity.designation.startsWith(designation)))
    );

    this.activityService.getActivities().subscribe(activities => {
      this.activities = activities;
    });
  }


  updateActivity() {
    this.err = JSON.parse(JSON.stringify(this.activityForm.get('category')?.value));
   this.activityService.updateActivity( <Activity>{
     id: 1,
     designation: this.activityForm.get('designation')?.value,
     description: this.activityForm.get('')?.value,
     startDate: this.activityForm.get('startDate')?.value,
     endDate: this.activityForm.get('endDate')?.value,
     price: Number(this.activityForm.get('price')?.value),
     category: this.err.at(0),
     descriptionDetail: this.activityForm.get('descriptionDetail')?.value,
     location: '',
     imageUrl: this.filename,
     maxParticipants: this.activityForm.get('maxParticipants')?.value,
     participants: this.activityForm.get('participants')?.value,
     isAvailableYearRound: this.activityForm.get('isAvailableYearRound')?.value,
     isFlexibleDates: this.activityForm.get('isFlexibleDates')?.value,
     isPlacesLimited: this.activityForm.get('isPlacesLimited')?.value,
     bookingStartDate: this.activityForm.get('bookingStartDate')?.value,
     bookingEndDate: this.activityForm.get('bookingEndDate')?.value,
     city: this.activityForm.get('city')?.value,
    },this.data.id).subscribe(activity => {
      this.activityForm.reset();
      },
       error => {
      this.alert.updateAlert(error.status);
      this.rout.navigate(['/activities'])
    }
   );
  }
  onCityChange(event: any)  {
    this.safeUrl = this.googleMapService.updateMap(event.value)
  }
}
