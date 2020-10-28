export interface IConcordanceResult {
  corpusId?: number;
  n_items?: number;
  search?: string;
  startDate?: number;
  endDate?: number;
  values?: IConcordanceContext[];
}

export class ConcordanceResult implements IConcordanceResult {
  constructor(
    public corpusId?: number,
    public n_items?: number,
    public search?: string,
    public startDate?: number,
    public endDate?: number,
    public values?: IConcordanceContext[]
  ) {}
}

export interface IConcordanceContext {
  source?: string;
  date?: string;
  leftContext?: string;
  kwic?: string;
  rightContext?: string;
}

export class ConcordanceContext implements IConcordanceContext {
  constructor(
    public source?: string,
    public date?: string,
    public leftContext?: string,
    public kwic?: string,
    public rightContext?: string
  ) {}
}
