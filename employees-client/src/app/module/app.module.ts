import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from '../app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angulat-material.module';
import { EmployeeDataService } from '../service/employee-data.service';
import { HttpErrorHandler } from '../service/http-error-handler.service';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [EmployeeDataService, HttpErrorHandler],
  bootstrap: [AppComponent]
})
export class AppModule { }
