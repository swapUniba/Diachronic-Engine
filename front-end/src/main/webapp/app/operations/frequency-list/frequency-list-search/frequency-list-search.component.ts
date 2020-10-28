import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Params, Router } from '@angular/router';
import { ChangeCorpusService } from 'app/layouts/corpus-selection/change-corpus.service';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { FrequencyListSearchFilter, FrequencyListSearchType } from 'app/shared/model/enumerations/frequency-list.model';

@Component({
  selector: 'jhi-frequency-list-search',
  templateUrl: './frequency-list-search.component.html',
  styleUrls: ['./frequency-list-search.component.scss']
})
export class FrequencyListSearchComponent implements OnInit {
  frequencyListSearchType = FrequencyListSearchType;
  frequencyListSearchFilter = FrequencyListSearchFilter;

  searchForm = this.fb.group({
    searchType: [FrequencyListSearchType.WORDS, [Validators.required]],
    searchFilter: [FrequencyListSearchFilter.ALL, [Validators.required]],
    searchFilterText: [],
    startDate: [],
    endDate: []
  });
  public workingCorpus: number;

  constructor(protected router: Router, private fb: FormBuilder, private changeCorpusService: ChangeCorpusService) {}

  ngOnInit(): void {
    this.changeCorpusService.currentCorpus.subscribe(corpusId => (this.workingCorpus = corpusId));
  }

  frequencyListRequest(): void {
    const params: Params = {
      corpusId: this.workingCorpus,
      searchType: this.searchForm.get('searchType')!.value,
      searchFilter: this.searchForm.get('searchFilter')!.value
    };
    if (this.searchForm.get('searchFilter')!.value !== 'ALL') {
      if (this.searchForm.get('searchFilterText')!.value === '' || this.searchForm.get('searchFilterText')!.value === null) {
        return;
      } else {
        params.searchFilterText = this.searchForm.get('searchFilterText')!.value;
      }
    }
    if (moment(this.searchForm.get('startDate')!.value).isValid()) {
      params.startDate = moment(this.searchForm.get('startDate')!.value).format(DATE_FORMAT);
    }
    if (moment(this.searchForm.get('endDate')!.value).isValid()) {
      params.endDate = moment(this.searchForm.get('endDate')!.value).format(DATE_FORMAT);
    }
    this.router
      .navigate(['frequency-list/result'], {
        queryParams: params
      })
      .then();
  }
}
