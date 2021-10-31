import { Component, ElementRef, ViewChild } from '@angular/core';
import { ProjectDetails } from './model/project-details.model';
import { EmployeeDataService } from './service/employee-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  @ViewChild('uploadFileInput')
  inputFile?: ElementRef;

  fileToUpload?: File;

  displayedColumns: string[] = ['firstEmployeeId', 'secondEmployeeId', 'projectId', 'daysWorked'];
  
  dataSource: ProjectDetails[] = [];

  constructor(private employeeDataService: EmployeeDataService) { }

  cleanSelection() {
    if (this.inputFile) {
      this.inputFile.nativeElement.value = "";
    }
    this.fileToUpload = undefined;
  }

  onChangeFileInput(eventTarget: any) {
    this.fileToUpload = eventTarget && eventTarget.files && eventTarget.files[0] || null;
  }

  showResults() {
    this.employeeDataService.send(this.fileToUpload)
      .subscribe(dataSource => {
        this.dataSource = dataSource;
        this.cleanSelection();
      });
  }

  get filename() {
    return this.fileToUpload && this.fileToUpload.name  || '';
  }

}
