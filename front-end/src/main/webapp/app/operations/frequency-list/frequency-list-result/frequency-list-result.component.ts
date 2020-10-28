import { Component, OnInit } from '@angular/core';
import { Moment } from 'moment';
import { ActivatedRoute, Params } from '@angular/router';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { IFrequencyListResult } from 'app/shared/model/frequency-list-result.model';
import { FrequencyListResultService } from 'app/operations/frequency-list/frequency-list-result/frequency-list-result.service';
import { FrequencyListSearchFilter } from 'app/shared/model/enumerations/frequency-list.model';

@Component({
  selector: 'jhi-frequency-list-result',
  templateUrl: './frequency-list-result.component.html',
  styleUrls: ['./frequency-list-result.component.scss']
})
export class FrequencyListResultComponent implements OnInit {
  frequencyListSearchFilter = FrequencyListSearchFilter;
  elements: Array<any>;
  ITEMS_PER_COLUMN = 10;
  corpusId?: number;
  searchType?: string;
  searchFilter?: string;
  searchFilterText?: string;
  startDate?: Moment;
  endDate?: Moment;
  frequencyListResults: IFrequencyListResult | null;

  totalItems = 0;
  itemsPerPage = this.ITEMS_PER_COLUMN * 6;
  page = 1;
  ngbPaginationPage = 1;

  constructor(protected activatedRoute: ActivatedRoute, protected frequencyListResultService: FrequencyListResultService) {}

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.corpusId = params['corpusId'];
      this.searchType = params['searchType'];
      this.searchFilter = params['searchFilter'];
      this.searchFilterText = params['searchFilterText'];
      this.startDate = params['startDate'];
      this.endDate = params['endDate'];
      this.getResult();
    });
  }

  getColumnNumber(): number {
    const columnNumber = this.frequencyListResults!.values!.length / this.ITEMS_PER_COLUMN;
    return Math.floor(columnNumber) === columnNumber ? columnNumber : columnNumber + 1;
  }

  // loadPage(page?: number): void {
  //   const pageToLoad: number = page || this.page;
  //   this.frequencyListResultService
  //     .query({
  //       page: pageToLoad - 1,
  //       size: this.itemsPerPage
  //     })
  //     .subscribe(
  //       (res: HttpResponse<IFrequencyListResult>) => this.onSuccess(res.body, res.headers, pageToLoad),
  //       () => this.onError()
  //     );
  // }

  protected onSuccess(data: IFrequencyListResult | null, headers: HttpHeaders, page: number): void {
    this.totalItems = data!.values!.length; // Number(headers.get('X-Total-Count'))
    this.page = page;
    this.ngbPaginationPage = this.page;
    this.frequencyListResults = data;
    this.elements = Array(this.getColumnNumber());
  }

  protected onError(): void {}

  private getResult(): void {
    const params: Params = {
      corpusId: this.corpusId,
      searchType: this.searchType,
      searchFilter: this.searchFilter
    };
    if (this.searchFilterText !== undefined) {
      params.searchFilterText = this.searchFilterText;
    }
    if (this.startDate !== undefined) {
      params.startDate = this.startDate;
    }
    if (this.endDate !== undefined) {
      params.endDate = this.endDate;
    }
    this.frequencyListResultService.query(params).subscribe(
      (res: HttpResponse<IFrequencyListResult>) => this.onSuccess(res.body, res.headers, this.page),
      () => this.onError()
    );
  }
}
