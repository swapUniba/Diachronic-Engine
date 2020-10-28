import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ChangeCorpusService {
  private messageSource = new BehaviorSubject(-1);
  currentCorpus = this.messageSource.asObservable();

  constructor() {}

  changeMessage(corpusId: number | undefined): void {
    if (corpusId != null) {
      this.messageSource.next(corpusId);
    }
  }
}
