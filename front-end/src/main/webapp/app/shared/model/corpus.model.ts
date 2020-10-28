export interface ICorpus {
  id?: number;
  name?: string;
  periodStart?: number;
  periodEnd?: number;
  isPublic?: boolean;
  languageId?: number;
  languageName?: string;
}

export class Corpus implements ICorpus {
  constructor(
    public id?: number,
    public name?: string,
    public periodStart?: number,
    public periodEnd?: number,
    public isPublic?: boolean,
    public languageId?: number,
    public languageName?: string
  ) {}
}
