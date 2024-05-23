import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-activity-detail',
  templateUrl: './activity-detail.component.html',
  styleUrls: ['./activity-detail.component.css']
})
export class ActivityDetailComponent {

  @Input('Designation') Designation!: string;
  @Input('FinishedDate') FinishedDate!: Date;
  @Input('Category') Category!: string;
  @Input('Status') Status!: number;

  @Output() deletedActivity = new EventEmitter();

  removeActivity() {
    this.deletedActivity.emit()
  }


  protected readonly status = status;
}
