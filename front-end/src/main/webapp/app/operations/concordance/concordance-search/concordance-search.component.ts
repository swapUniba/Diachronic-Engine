import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Params, Router } from '@angular/router';
import { ChangeCorpusService } from 'app/layouts/corpus-selection/change-corpus.service';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

@Component({
  selector: 'jhi-concordance-search',
  templateUrl: './concordance-search.component.html',
  styleUrls: ['./concordance-search.component.scss']
})
export class ConcordanceSearchComponent implements OnInit {
  searchForm = this.fb.group({
    search: ['', [Validators.required]],
    startDate: [],
    endDate: []
  });
  public workingCorpus: number;

  constructor(protected router: Router, private fb: FormBuilder, private changeCorpusService: ChangeCorpusService) {}

  ngOnInit(): void {
    this.changeCorpusService.currentCorpus.subscribe(corpusId => (this.workingCorpus = corpusId));
  }

  concordanceRequest(): void {
    const params: Params = {
      corpusId: this.workingCorpus,
      search: this.searchForm.get('search')!.value
    };
    if (moment(this.searchForm.get('startDate')!.value).isValid()) {
      params.startDate = moment(this.searchForm.get('startDate')!.value).format(DATE_FORMAT);
    }
    if (moment(this.searchForm.get('endDate')!.value).isValid()) {
      params.endDate = moment(this.searchForm.get('endDate')!.value).format(DATE_FORMAT);
    }
    this.router
      .navigate(['concordance/result'], {
        queryParams: params
      })
      .then();
  }
}
