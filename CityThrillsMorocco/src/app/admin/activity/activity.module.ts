import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatOptionModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {MatListModule} from '@angular/material/list';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';
import {MatPaginatorModule} from '@angular/material/paginator';
import {DragDropModule} from '@angular/cdk/drag-drop';


import {RouterLink} from "@angular/router";
import {ActivityUpdateComponent} from './activity-update/activity-update.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatCardModule} from "@angular/material/card";
import {ActivityListComponent} from "./activity-list/activity-list.component";
import {ActivityCreateComponent} from "./activity-create/activity-create.component";
import {ActivityShowComponent} from "./activity-show/activity-show.component";
import {ActivityDetailComponent} from "./activity-detail/activity-detail.component";
import {ActivitiesComponent} from "./activities/activities.component";
import {ActivityRoutingModule} from "./activity-routing.module";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatIconModule} from "@angular/material/icon";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatToolbarModule} from "@angular/material/toolbar";
import {ClipboardModule} from "@angular/cdk/clipboard";
import {GoogleMapsModule} from "@angular/google-maps";
import {YouTubePlayerModule} from "@angular/youtube-player";



@NgModule({
  declarations: [
    ActivityUpdateComponent,
    ActivityListComponent,
    ActivityCreateComponent,
    ActivityShowComponent,
    ActivityDetailComponent,
    ActivitiesComponent,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  imports: [
    ActivityRoutingModule,
    CommonModule,
    DragDropModule,
    MatGridListModule,
    MatListModule,
    FormsModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    RouterLink,
    MatDialogModule,
    MatButtonToggleModule,
    NgOptimizedImage,
    MatCardModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCheckboxModule,

    ReactiveFormsModule,
    CommonModule,
    MatToolbarModule,
    ClipboardModule,
    MatFormFieldModule,
    FormsModule,
    GoogleMapsModule,
    YouTubePlayerModule,
    MatGridListModule,

  ],
  exports: [],
})
export class ActivityModule { }
