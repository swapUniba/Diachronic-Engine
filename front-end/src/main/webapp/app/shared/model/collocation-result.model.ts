import { CollocationType } from 'app/shared/model/enumerations/collocation.model';

export interface ICollocationResult {
  corpusId?: number;
  search?: string;
  startDate?: number;
  endDate?: number;
  type?: CollocationType;
  values?: string[];
}

export class CollocationResult implements ICollocationResult {
  constructor(
    public corpusId?: number,
    public search?: string,
    public startDate?: number,
    public endDate?: number,
    public type?: CollocationType,
    public values?: string[]
  ) {}
}
