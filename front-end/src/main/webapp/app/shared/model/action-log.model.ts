import { Action } from 'app/shared/model/enumerations/action.model';

export interface IActionLog {
  id?: number;
  corpusId?: number;
  corpusName?: string;
  action?: Action;
  request?: string;
  createdDate?: string;
}

export class ActionLog implements IActionLog {
  constructor(
    public id?: number,
    public corpusId?: number,
    public corpusName?: string,
    public action?: Action,
    public request?: string,
    public createdDate?: string
  ) {}
}
