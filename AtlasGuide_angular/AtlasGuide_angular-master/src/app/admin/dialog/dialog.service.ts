import { Injectable } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {
  DeleteConfirmationDialogComponent
} from "../activity/delete-confirmation-dialog/delete-confirmation-dialog.component";
import {ActivityService} from "../activity/activity.service";
import {Observable} from "rxjs";
import {ActivityUpdateComponent} from "../activity/activity-update/activity-update.component";

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(
    private dialog: MatDialog,
    private activityService: ActivityService
  ) { }

  DeleteConfirmationDialog(id: number): void{
    const dialogRef = this.dialog.open(DeleteConfirmationDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.activityService.getActivity(id).subscribe(
          ac => console.log(ac.city)
        );
        this.activityService.deleteActivity(id)
      }
    });
  }


  UpdateDialog(id: number): void {
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
