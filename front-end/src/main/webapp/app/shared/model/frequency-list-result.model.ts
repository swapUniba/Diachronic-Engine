export interface IFrequencyListResult {
  corpusId?: number;
  searchType?: string;
  searchFilter?: string;
  searchFilterText?: string;
  startDate?: number;
  endDate?: number;
  values?: IFrequencyItem[];
}

export class FrequencyListResult implements IFrequencyListResult {
  constructor(
    public corpusId?: number,
    public searchType?: string,
    public searchFilter?: string,
    public searchFilterText?: string,
    public startDate?: number,
    public endDate?: number,
    public values?: IFrequencyItem[]
  ) {}
}

export interface IFrequencyItem {
  word?: string;
  frequency?: number;
}

export class FrequencyItem implements IFrequencyItem {
  constructor(public word?: string, public frequency?: number) {}
}
