import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Params, Router } from '@angular/router';
import { ChangeCorpusService } from 'app/layouts/corpus-selection/change-corpus.service';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

@Component({
  selector: 'jhi-time-series-search',
  templateUrl: './time-series-search.component.html',
  styleUrls: ['./time-series-search.component.scss']
})
export class TimeSeriesSearchComponent implements OnInit {
  searchForm = this.fb.group({
    searchTerms: this.fb.array([this.generateTermForm()]),
    startDate: [],
    endDate: []
  });
  public workingCorpus: number;

  constructor(protected router: Router, private fb: FormBuilder, private changeCorpusService: ChangeCorpusService) {}

  ngOnInit(): void {
    this.changeCorpusService.currentCorpus.subscribe(corpusId => (this.workingCorpus = corpusId));
  }

  getTermsAsControls(): Array<AbstractControl> {
    return (this.searchForm.get('searchTerms') as FormArray).controls;
  }

  getTermsLength(): number {
    return (this.searchForm.get('searchTerms') as FormArray).length;
  }

  addTerm(): void {
    (this.searchForm.get('searchTerms') as FormArray).push(this.generateTermForm());
  }

  deleteTerm(index: number): void {
    (this.searchForm.get('searchTerms') as FormArray).removeAt(index);
  }

  timeSeriesRequest(): void {
    const params: Params = {
      corpusId: this.workingCorpus,
      searchTerms: (this.searchForm.get('searchTerms') as FormArray).controls.map(term => term.get('term')!.value).join(',')
    };
    if (moment(this.searchForm.get('startDate')!.value).isValid()) {
      params.startDate = moment(this.searchForm.get('startDate')!.value).format(DATE_FORMAT);
    }
    if (moment(this.searchForm.get('endDate')!.value).isValid()) {
      params.endDate = moment(this.searchForm.get('endDate')!.value).format(DATE_FORMAT);
    }
    this.router
      .navigate(['time-series/result'], {
        queryParams: params
      })
      .then();
  }

  private generateTermForm(): FormGroup {
    return this.fb.group({
      term: ['', [Validators.required]]
    });
  }
}
