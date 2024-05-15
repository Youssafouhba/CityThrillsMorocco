import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-errors',
  templateUrl: './errors.component.html',
  styleUrls: ['./errors.component.css']
})
export class ErrorsComponent implements OnInit{

  constructor(public dialogRef: MatDialogRef<ErrorsComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,) {}

  ngOnInit() {
    console.log(this.data.value)
  }

  onCancel(): void {
    this.dialogRef.close(false);
  }
}
