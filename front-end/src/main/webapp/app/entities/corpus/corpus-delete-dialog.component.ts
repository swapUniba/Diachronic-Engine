import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICorpus } from 'app/shared/model/corpus.model';
import { CorpusService } from './corpus.service';

@Component({
  templateUrl: './corpus-delete-dialog.component.html'
})
export class CorpusDeleteDialogComponent {
  corpus?: ICorpus;

  constructor(protected corpusService: CorpusService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.corpusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('corpusListModification');
      this.activeModal.close();
    });
  }
}
