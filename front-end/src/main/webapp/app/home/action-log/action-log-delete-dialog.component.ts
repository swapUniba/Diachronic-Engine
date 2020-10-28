import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IActionLog } from 'app/shared/model/action-log.model';
import { ActionLogService } from 'app/home/action-log/action-log.service';

@Component({
  templateUrl: './action-log-delete-dialog.component.html'
})
export class ActionLogDeleteDialogComponent {
  actionLog?: IActionLog;

  constructor(protected actionLogService: ActionLogService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.actionLogService.delete(id).subscribe(() => {
      this.eventManager.broadcast('actionLogListModification');
      this.activeModal.close();
    });
  }
}
