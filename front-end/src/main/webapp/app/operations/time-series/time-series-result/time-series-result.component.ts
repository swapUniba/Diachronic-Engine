import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Moment } from 'moment';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ITimeSeriesListResult } from 'app/shared/model/time-series-result.model';
import { ITimeSeriesItem } from 'app/shared/model/time-series-result.model';
import { TimeSeriesResultService } from 'app/operations/time-series/time-series-result/time-series-result.service';

@Component({
  selector: 'jhi-time-series-result',
  templateUrl: './time-series-result.component.html',
  styleUrls: ['./time-series-result.component.scss']
})
export class TimeSeriesResultComponent implements OnInit {
  options: any;
  corpusId?: number;
  searchTerms?: string;
  firstTerms?: string[];
  secondTerms?: string[];
  terms = '';
  startDate?: Moment;
  endDate?: Moment;
  shiftingPointsEnabled = false;
  noShiftingPointOptions = {};
  shiftingPointOptions = {};

  constructor(
    protected activatedRoute: ActivatedRoute,
    private changeDetector: ChangeDetectorRef,
    private timeSeriesResultService: TimeSeriesResultService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.corpusId = params['corpusId'];
      this.searchTerms = params['searchTerms'];
      this.firstTerms = params['firstTerms'] ? params['firstTerms'].split(',') : params['firstTerms'];
      this.secondTerms = params['secondTerms'] ? params['secondTerms'].split(',') : params['secondTerms'];
      this.startDate = params['startDate'];
      this.endDate = params['endDate'];
    });
    this.getResult();
    this.mapTerms();
    this.options = this.noShiftingPointOptions;
  }

  switchShiftingPoints(): void {
    this.shiftingPointsEnabled = !this.shiftingPointsEnabled;
    this.options = this.shiftingPointsEnabled ? this.shiftingPointOptions : this.noShiftingPointOptions;
    this.changeDetector.detectChanges();
  }

  private mapTerms(): void {
    if (this.firstTerms && this.secondTerms) {
      const len = this.firstTerms.length;
      for (let i = 0; i < len; i++) {
        this.terms += this.firstTerms[i] + '-' + this.secondTerms[i];
        if (i !== len - 1) {
          this.terms += ', ';
        }
      }
    } else if (this.searchTerms) {
      this.terms = this.searchTerms;
    }
  }

  protected onSuccess(data: ITimeSeriesListResult | null, headers: HttpHeaders): void {
    this.noShiftingPointOptions["tooltip"] = {};
    this.shiftingPointOptions["tooltip"] = {};
    
    var legend = {};
    legend["align"] = 'left';
    var xAxis = {};
    xAxis["type"] = 'category';
    var yAxis = {};
    yAxis["type"] = 'value';

    var itemStyle = {borderWidth: 5,
          borderColor: 'red',
          color: 'red'};
    var lineStyle = {
          width: 0};

    this.noShiftingPointOptions["legend"] = legend;
    this.shiftingPointOptions["legend"] = legend;
    this.noShiftingPointOptions["xAxis"] = xAxis;
    this.shiftingPointOptions["xAxis"] = xAxis;
    this.noShiftingPointOptions["yAxis"] = yAxis;
    this.shiftingPointOptions["yAxis"] = yAxis;
    this.noShiftingPointOptions["series"] = [];
    this.shiftingPointOptions["series"] = [];

    this.noShiftingPointOptions["legend"]["data"] = data.words;
    this.shiftingPointOptions["legend"]["data"] = data.words;
    this.noShiftingPointOptions["xAxis"]["data"] = data.xlabels;
    this.shiftingPointOptions["xAxis"]["data"] = data.xlabels;

    for(let i of data.values){
       var item = {name:i.word, type: 'line', data: i.values};
       this.noShiftingPointOptions["series"].push(item);
       this.shiftingPointOptions["series"].push(item);
    }

    for(let i of data.changepoints){
        var changepoint = {name: i.word, type: "line", symbolsize: 10, lineStyle: lineStyle, itemStyle: itemStyle, data: i.values};
        this.shiftingPointOptions["series"].push(changepoint);
    }
  }

  protected onError(): void {}

  private getResult(): void {
    const params: Params = {
      corpusId: this.corpusId
    };
    if (this.searchTerms !== undefined) {
      params.searchTerms = this.searchTerms;
    }
    if (this.firstTerms !== undefined) {
      params.firstTerms = this.firstTerms;
    }
    if (this.secondTerms !== undefined) {
      params.secondTerms = this.secondTerms;
    }
    if (this.startDate !== undefined) {
      params.startDate = this.startDate;
    }
    if (this.endDate !== undefined) {
      params.endDate = this.endDate;
    }
    this.timeSeriesResultService.query(params).subscribe(
      (res: HttpResponse<ITimeSeriesListResult>) => this.onSuccess(res.body, res.headers),
      () => this.onError()
    );
  }
}
