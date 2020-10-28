import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Moment } from 'moment';
import { HttpResponse } from '@angular/common/http';
import { CollocationResultService } from 'app/operations/collocation/collocation-result/collocation-result.service';
import { ICollocationResult } from 'app/shared/model/collocation-result.model';
import { CollocationType } from 'app/shared/model/enumerations/collocation.model';

@Component({
  selector: 'jhi-collocation-result',
  templateUrl: './collocation-result.component.html',
  styleUrls: ['./collocation-result.component.scss']
})
export class CollocationResultComponent implements OnInit {
  collocationType: CollocationType;
  corpusId?: number;
  search?: string;
  startDate?: Moment;
  endDate?: Moment;
  collocationResults: ICollocationResult[] | null;

  constructor(protected activatedRoute: ActivatedRoute, protected collocationResultService: CollocationResultService) {}

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.corpusId = params['corpusId'];
      this.search = params['search'];
      this.startDate = params['startDate'];
      this.endDate = params['endDate'];
      this.getResult();
    });
  }

  protected onSuccess(data: ICollocationResult[] | null): void {
    this.collocationResults = data;
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
    this.collocationResultService.query(params).subscribe(
      (res: HttpResponse<ICollocationResult[]>) => this.onSuccess(res.body),
      () => this.onError()
    );
  }
}
