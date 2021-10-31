export class ProjectDetails {
    _firstEmployeeId: number;
    _secondEmployeeId: number;
    _projectId: number;
    _daysWorked: number;

    constructor(firstEmployeeId: number, secondEmployeeId: number, projectId: number, daysWorked: number) {
        this._firstEmployeeId = firstEmployeeId;
        this._secondEmployeeId = secondEmployeeId;
        this._projectId = projectId;
        this._daysWorked = daysWorked;
    }

    get firstEmployeeId() {
        return this._firstEmployeeId;
    }

    get secondEmployeeId() {
        return this._secondEmployeeId;
    }

    get projectId() {
        return this._projectId;
    }

    get daysWorked() {
        return this._daysWorked;
    }

  }