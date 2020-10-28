import { Component, OnInit } from '@angular/core';
import { Moment } from 'moment';
import { ActivatedRoute, Params } from '@angular/router';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { NgxLoadingModule } from 'ngx-loading';
import { IConcordanceResult } from 'app/shared/model/concordance-result.model';
import { ConcordanceResultService } from 'app/operations/concordance/concordance-result/concordance-result.service';
import { SnackbarUtil } from 'app/shared/util/snackbar-util';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';

@Component({
  selector: 'jhi-concordance-result',
  templateUrl: './concordance-result.component.html',
  styleUrls: ['./concordance-result.component.scss'],
  providers: [SnackbarUtil]
})
export class ConcordanceResultComponent implements OnInit {
  corpusId?: number;
  search?: string;
  startDate?: Moment;
  endDate?: Moment;
  concordanceResults: IConcordanceResult | null;
  totalItems = 0;
  loading: boolean = true;
  itemsPerPage = ITEMS_PER_PAGE;
  page = 1;
  ngbPaginationPage = 1;

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected concordanceResultService: ConcordanceResultService,
    private snackBar: SnackbarUtil
  ) {}

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.corpusId = params['corpusId'];
      this.search = params['search'];
      this.startDate = params['startDate'];
      this.endDate = params['endDate'];
      this.getResult();
    });
  }

  showCopiedMessage(): void {
    this.snackBar.snackSuccess('concordance.copySuccessful');
  }

  loadPage(page?: number): void {
     const pageToLoad: number = page || this.page;
     const params: Params = {
           corpusId: this.corpusId,
           search: this.search
     };
     params.page = pageToLoad - 1;
     if (this.startDate !== undefined) {
       params.startDate = this.startDate;
     }
     if (this.endDate !== undefined) {
       params.endDate = this.endDate;
     }
     this.concordanceResultService
         .query(params)
         .subscribe(
           (res: HttpResponse<IConcordanceResult>) => {this.onSuccess(res.body, res.headers, pageToLoad);},
           () => {this.onError();}
        );
  }

  protected onSuccess(data: IConcordanceResult | null, headers: HttpHeaders, page: number): void {
    this.concordanceResults = data;
    this.totalItems = data!.n_items; // Number(headers.get('X-Total-Count'))
    this.page = page;
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {}

  private getResult(): void {
    const params: Params = {
      corpusId: this.corpusId,
      search: this.search
    };
    if (this.startDate !== undefined) {
      params.startDate = this.startDate;
    }
    if (this.endDate !== undefined) {
      params.endDate = this.endDate;
    }
    params.page = this.page - 1;
    this.loading = true;
    this.concordanceResultService
         .query(params)
         .subscribe(
           (res: HttpResponse<IConcordanceResult>) => {this.loading=false; this.onSuccess(res.body, res.headers, params.page);},
           () => {this.loading=false; this.onError();}
        );
  }
}
