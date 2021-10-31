import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
    exports: [
        MatButtonModule,
        MatInputModule, 
        MatFormFieldModule,
        MatTableModule,
        MatIconModule,
        MatSnackBarModule
    ],
    imports: [
        MatButtonModule,
        MatInputModule, 
        MatFormFieldModule,
        MatTableModule,
        MatIconModule,
        MatSnackBarModule
    ]
  })
export class AngularMaterialModule {

}