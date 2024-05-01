import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Activity} from "../../../models/Activity";
import {ActivityService} from "../activity.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {priceRangeValidator} from "../../products/price-range.directive";
import {map, Observable} from "rxjs";
import {Time} from "@angular/common";
import {SessionService} from "../../session.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-activity-create',
  templateUrl: './activity-create.component.html',
  styleUrls: ['./activity-create.component.css']
})
export class ActivityCreateComponent implements OnInit{

  @Output() added = new EventEmitter<Activity>();

  filename = "";
  constructor(private activityService: ActivityService,
              private sessionService: SessionService,
              private router: Router) {
  }

  activityForm = new FormGroup({
    designation: new FormControl('', {
      nonNullable: true,
      validators: Validators.required
    }),
    description: new FormControl('', {
      nonNullable: true,
      validators: Validators.required
    }),
    starteddate: new FormControl<Date | any  >( undefined,{
      nonNullable: true,
      validators: Validators.required
    }),
    finisheddate: new FormControl<Date | any>(undefined, {
      nonNullable: true,
      validators: Validators.required
    }),
    startedduration: new FormControl<Time | any>(undefined,{
      nonNullable: true,
      validators: Validators.required
    }),
    finishedduration: new FormControl<Time | any>(undefined, {
      nonNullable: true,
      validators: Validators.required
    }),
    price: new FormControl<number | undefined>(undefined, {
      nonNullable: true,
      validators: [Validators.required, priceRangeValidator()]
    }),
    category: new FormControl<String | ''>('', {
      nonNullable: true,
      validators: Validators.required
    }),
    descriptiondetail:  new FormControl('', {
      nonNullable: true,
      validators: Validators.required
    }),
    location: new FormControl('', {
      nonNullable: true,
      validators: Validators.required
    }),
    imageUrl: new FormControl('', {
      nonNullable: true,
      validators: Validators.required
    }),
  });

  showPriceRangeHint = false;
  activities: Activity[] = [];
  activities$: Observable<Activity[]> | undefined;
  categories = ['Hardware', 'Computers', 'Clothing', 'Software'];

  err: string[] = [];

  get designation() { return this.activityForm.controls.designation }
  get description() { return this.activityForm.controls.description }
  get descriptiondetail() { return this.activityForm.controls.descriptiondetail }
  get startedduration() { return this.activityForm.controls.startedduration }
  get finishedduration() { return this.activityForm.controls.finishedduration }
  get starteddate() { return this.activityForm.controls.starteddate }
  get finisheddate() { return this.activityForm.controls.finisheddate }
  get category() { return this.activityForm.controls.category }
  get price() { return this.activityForm.controls.price }
  get location() { return this.activityForm.controls.location }
  get imageUrl(){return this.activityForm.controls.imageUrl}

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    this.filename = file.name;
  }

  ngOnInit(): void {
    const user = this.sessionService.getSessionData('user');
    if (!user){
      this.router.navigate(["/admin"])
    }
    this.price.valueChanges.subscribe(price => {
      if (price) {
        this.showPriceRangeHint = price > 1 && price < 10000;
      }
    });
    this.activityService.getActivities().subscribe(activities => {
      this.activities = activities;
    });
    this.activities$ = this.designation.valueChanges.pipe(
      map(designation => this.activities.filter(activity => activity.designation.startsWith(designation)))
    );
  }

  createActivity() {
    this.err = JSON.parse(JSON.stringify(this.category.value));
    this.activityService.addActivity( <Activity>{
      id: 1,
      designation: this.designation.value,
      description: this.description.value,
      starteddate: this.starteddate.value,
      finisheddate: this.finisheddate.value,
      startedduration: this.startedduration.value,
      finishedduration: this.finishedduration.value,
      price: Number(this.price.value),
      category: this.err.at(0),
      descriptiondetail: this.descriptiondetail.value,
      location: this.location.value,
      imageUrl: this.filename
    }).subscribe(activity => {
      this.activityForm.reset();
      this.added.emit(activity);
    });
  }


}
