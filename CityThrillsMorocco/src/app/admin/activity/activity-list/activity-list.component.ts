import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Activity} from "../../../models/Activity";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {ActivityService} from "../activity.service";
import {ActivatedRoute, Router} from "@angular/router";
import { MatDialog } from '@angular/material/dialog';
import {ActivityUpdateComponent} from "../activity-update/activity-update.component";
import {DeleteConfirmationDialogComponent} from "../delete-confirmation-dialog/delete-confirmation-dialog.component";
import {SessionService} from "../../session.service";

@Component({
  selector: 'app-activity-list',
  templateUrl: './activity-list.component.html',
  styleUrls: ['./activity-list.component.css']
})
export class ActivityListComponent implements OnInit{

  status = '';

  selectedActivity: Activity | undefined;
  activities = new MatTableDataSource<Activity>([]);
  activities$: Activity[] = [];


  @ViewChild(MatSort) sort: MatSort | null = null;
  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;

  constructor(private activityService: ActivityService,
              private route: ActivatedRoute,
              private dialog: MatDialog,
              private sessionService: SessionService,
              private router: Router) {}

  ngOnInit(): void {
    this.getActivities();
    const queryParams = this.route.snapshot.queryParams;
    this.status = queryParams['status'];

    const user = this.sessionService.getSessionData('user');
    if (!user){
      this.router.navigate(["/admin"])
    }
  }

  private getActivities() {
    this.activityService.getActivities().subscribe(activities => {
      this.activities = new MatTableDataSource(activities);
      this.activities$ = activities;
      this.activities.sort = this.sort;
      this.activities.paginator = this.paginator;
    });
  }



  isDatePassed(date: Date): boolean {
    const queryParams = this.route.snapshot.queryParams;
    this.status = queryParams['status'];
    if (this.status === 'Active') {
      return date && new Date(date) < new Date();
    }else {
      return date && new Date(date) > new Date();
    }
  }

  openDeleteConfirmationDialog(id: number): void {
    const dialogRef = this.dialog.open(DeleteConfirmationDialogComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.activityService.deleteActivity(id).subscribe(() => {
          this.getActivities();
        });}
    });
  }


  openUpdateDialog(id: number): void {
    const dialogRef = this.dialog.open(ActivityUpdateComponent, {
      width: '900px',
      height: '600px',
      data: { id: id},
      position: {
        top: '100px'
      }
    });

    dialogRef.afterOpened().subscribe(() => {
      // Focus sur le haut de la boîte de dialogue
      const dialogElement = document.querySelector('.mat-dialog-container');
      if (dialogElement) {
        dialogElement.scrollTop = 100;
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // Optionally, you can perform any action after the dialog is closed
    });
}
}
