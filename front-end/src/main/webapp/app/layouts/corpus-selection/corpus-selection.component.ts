import { Component, OnInit } from '@angular/core';
import { ICorpus } from 'app/shared/model/corpus.model';
import { HttpResponse } from '@angular/common/http';
import { CorpusService } from 'app/entities/corpus/corpus.service';
import { ChangeCorpusService } from 'app/layouts/corpus-selection/change-corpus.service';
import { Router } from '@angular/router';
import { LocalStorageService } from 'ngx-webstorage';
import { MatOptionSelectionChange } from '@angular/material/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'jhi-corpus-selection',
  templateUrl: './corpus-selection.component.html',
  styleUrls: ['./corpus-selection.component.scss']
})
export class CorpusSelectionComponent implements OnInit {
  corpusSearchValue = '';
  corpora?: ICorpus[];
  workingCorpus?: number;
  selectForm = this.fb.group({
    selectCorpus: ['', [Validators.required]]
  });

  constructor(
    protected corpusService: CorpusService,
    private changeCorpusService: ChangeCorpusService,
    protected router: Router,
    private localStorage: LocalStorageService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.loadCorpora();
  }

  loadCorpora(): void {
    this.corpusService.query({}).subscribe((res: HttpResponse<ICorpus[]>) => this.onSuccess(res.body));
  }

  setWorkingCorpus(corpusId: MatOptionSelectionChange): void {
    const id = corpusId.source.value;
    if (corpusId.isUserInput && id !== this.workingCorpus) {
      this.changeCorpusService.changeMessage(id);
      if (this.workingCorpus !== null && this.router.url.includes('/result')) {
        this.router.navigate(['/']).then();
      }
      this.workingCorpus = id;
      this.localStorage.store('workingCorpus', this.workingCorpus);
    }
  }

  hidden(): boolean {
    const url = this.router.url;
    return url.startsWith('/corpus') || url.startsWith('/language') || url.startsWith('/admin');
  }

  protected onSuccess(data: ICorpus[] | null): void {
    this.corpora = data || [];
    const cacheCorpusId = this.localStorage.retrieve('workingCorpus');
    if (cacheCorpusId && this.corpora.map(corpus => corpus.id).includes(parseInt(cacheCorpusId, 10))) {
      this.changeCorpusService.changeMessage(cacheCorpusId);
      this.workingCorpus = cacheCorpusId;
      this.selectForm.patchValue({
        selectCorpus: cacheCorpusId
      });
    }
  }
}
