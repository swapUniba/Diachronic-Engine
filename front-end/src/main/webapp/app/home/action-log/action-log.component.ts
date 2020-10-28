import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActionLogService } from 'app/home/action-log/action-log.service';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { IActionLog } from 'app/shared/model/action-log.model';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { ActionLogDeleteDialogComponent } from 'app/home/action-log/action-log-delete-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-action-log',
  templateUrl: './action-log.component.html',
  styleUrls: ['./action-log.component.scss']
})
export class ActionLogComponent implements OnInit, OnDestroy {
  actionLogs?: IActionLog[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = 5;
  page = 0;
  ngbPaginationPage = 1;

  constructor(
    protected actionLogService: ActionLogService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.loadPage();
    this.registerChangeInActionLogs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;
    this.actionLogService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage
      })
      .subscribe(
        (res: HttpResponse<IActionLog[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  trackId(index: number, item: IActionLog): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInActionLogs(): void {
    this.eventSubscriber = this.eventManager.subscribe('actionLogListModification', () => this.loadPage());
  }

  delete(actionLog: IActionLog): void {
    const modalRef = this.modalService.open(ActionLogDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.actionLog = actionLog;
  }

  protected onSuccess(data: IActionLog[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.ngbPaginationPage = this.page;
    this.actionLogs = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
