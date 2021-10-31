import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProjectDetails } from '../model/project-details.model';
import { ResponseData } from '../model/response-data.model';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { HandleError, HttpErrorHandler } from './http-error-handler.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
    providedIn: 'root',
})
export class EmployeeDataService {

    _dataSource: ProjectDetails[];
    private handleError: HandleError;

    constructor(private http: HttpClient, private httpErrorHandler: HttpErrorHandler, private _snackBar: MatSnackBar) {
        this._dataSource = [];
        this.handleError = httpErrorHandler.createHandleError('EmployeeDataService');
    }

    send(file: any) : Observable<ProjectDetails[]> {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'multipart/form-data');

        const options = { headers: headers };
        const formData: FormData = new FormData();
        formData.append('file', file, file.name);

        return this.http.post('http://localhost:8080/employees', formData, options)
            .pipe(
                map(response => this.mapResponse(response)),
                catchError(this.handleError('fetch', []))
            );
    }

    private mapResponse(response: ResponseData<ProjectDetails[]>) : ProjectDetails[] {
        if (response.data) {
            return response.data;
        }
        if (response.error) {
            this._snackBar.open(response.error, "Close");
        }
        return [];
    }

}
