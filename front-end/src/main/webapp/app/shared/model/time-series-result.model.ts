export interface ITimeSeriesListResult {
    corpusId?: number;
    searchTerms?: string[];
    startDate?: number;
    endDate?: number;
    words?: string[];
    xlabels? : string[];
    values? : ITimeSeriesItem[];
    changepoints? : ITimeSeriesItem[];
}

export class TimeSeriesListResult implements ITimeSeriesListResult {
  constructor(
    public corpusId?: number,
    public searchTerms?: string[],
    public startDate?: number,
    public endDate?: number,
    public xlabels? : string[],
    public values? : ITimeSeriesItem[]
  ) {}
}

export interface ITimeSeriesItem {
    word?: string;
    values?: number[];
}

export class ITimeSeriesItem {
  constructor(
    public word?: string,
    public values?: number[]
  ) {}
}
