export interface ILanguage {
  id?: number;
  name?: string;
  code?: string;
}

export class Language implements ILanguage {
  constructor(public id?: number, public name?: string, public code?: string) {}
}
