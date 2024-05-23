import {AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Activity} from "../../../models/Activity";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {ActivityService} from "../activity.service";
import {ActivatedRoute} from "@angular/router";
import {MatDialog} from '@angular/material/dialog';
import {ActivityUpdateComponent} from "../activity-update/activity-update.component";
import {DeleteConfirmationDialogComponent} from "../delete-confirmation-dialog/delete-confirmation-dialog.component";
import {AuthentificationService} from "../../authentification/authentification.service"
import {PipsService} from "../../pips/pips.service";
import {FiltersService} from "../../filter/filters.service";
import {SessionService} from "../../session.service";
import {DialogService} from "../../dialog/dialog.service";
import {Reservation} from "../../../models/Reservation";
import {User} from "../../../../model/User.model";
@Component({
  selector: 'app-activity-list',
  templateUrl: './activity-list.component.html',
  styleUrls: ['./activity-list.component.css']
})
export class ActivityListComponent implements OnInit,AfterViewInit{
  user!: User;
  status = '';
  activities = new MatTableDataSource<Activity>([]);
  activities$: Activity[] = [];

  types = [{name: "Year Round",value: true},{name: "Periodic",value: false}];
  stateFilters = ["Active","Expired","Pending"];
  rowStyles: string[] = ['t','table-light'];
  rowIcon: string[] = ["mdi mdi-waveform mdi-20px text-success me-3","mdi mdi-checkbox-marked-circle-plus-outline mdi-20px text-danger me-3"]
  @ViewChild(MatSort) sort: MatSort | null = null;
  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;
  reservation!: Reservation;
  private webSocket: WebSocket;

  constructor(
    private activityService: ActivityService,
    private AuthenticatinService: AuthentificationService,
    protected Pipe: PipsService,
    protected filterService: FiltersService,
    private cdr: ChangeDetectorRef,
    private session: SessionService,
    private dialog: DialogService,
    private sessionService: SessionService
    ) {
    this.webSocket = new WebSocket('ws://localhost:8080/activity?userId='+this.sessionService.getSessionData('user').Uid);
  }

  ngOnInit(): void {
    this.getAgencyActivities()
    this.AuthenticatinService.isAuthenticated()
  }

  private getAgencyActivities() {
    this.activityService.getAgencyActivitiesById(this.session.getSessionData('user').Uid).subscribe(activities => {
      this.activities = new MatTableDataSource(activities);
      this.activities$ = activities;
      this.activities.sort = this.sort;
      this.activities.paginator = this.paginator;
    });
  }

  // Dialogues //
  openDeleteConfirmationDialog(id: number): void {
    console.log(id)
    this.dialog.DeleteConfirmationDialog(id)
    this.getAgencyActivities()
  }

  openUpdateDialog(id: number): void {
    this.dialog.UpdateDialog(id)
  }

  // Filters //
  typeFilter(value: boolean) {
    this.activities.data = this.filterService.applyFilter(value,this.activities$)
  }
  stateFilter(value: string) {
    this.activities.data = this.filterService.StateFilter(value,this.activities$)
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.generateRandomBinaryDigit()
      this.cdr.detectChanges();
    });
  }
  generateRandomBinaryDigit() {return Math.round(Math.random());}

}
