import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { Client } from '@stomp/stompjs';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatToolbarModule} from '@angular/material/toolbar';
import {ClipboardModule} from '@angular/cdk/clipboard';
import {GoogleMapsModule} from '@angular/google-maps';
import {YouTubePlayerModule} from '@angular/youtube-player';
import {MatNativeDateModule} from "@angular/material/core";

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AdminModule} from "./admin/admin.module";
import {ButtonsComponent} from './buttons/buttons.component';
import {CartComponent} from './cart/cart.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {AuthModule} from './auth/auth.module';
import {CopyTextComponent} from './copy-text/copy-text.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MapComponent} from './map/map.component';
import {PlayerComponent} from './player/player.component';
import {MatGridListModule} from '@angular/material/grid-list';

@NgModule({
    declarations: [
        AppComponent,
        ButtonsComponent,
        CartComponent,
        PageNotFoundComponent,
        CopyTextComponent,
        MapComponent,
        PlayerComponent,
    ],
    imports: [
        HttpClientModule,
        AuthModule,

        AppRoutingModule,
        MatNativeDateModule,
        BrowserModule,
        BrowserAnimationsModule,
        MatIconModule,
        MatButtonModule,
        MatButtonToggleModule,
        MatCheckboxModule,
      AdminModule,
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
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    providers: [Client],
  exports: [

  ],
    bootstrap: [AppComponent]
})
export class AppModule { }
