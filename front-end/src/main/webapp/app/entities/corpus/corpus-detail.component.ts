import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Corpus, ICorpus } from 'app/shared/model/corpus.model';
import { CorpusService } from 'app/entities/corpus/corpus.service';

@Component({
  selector: 'jhi-corpus-detail',
  templateUrl: './corpus-detail.component.html'
})
export class CorpusDetailComponent implements OnInit {
  corpus: ICorpus | null = null;

  constructor(protected activatedRoute: ActivatedRoute, protected corpusService: CorpusService) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ corpus }) => (this.corpus = corpus));
  }

  setPublic(corpus: Corpus, isPublic: boolean): void {
    this.corpusService.update({ ...corpus, isPublic }).subscribe(() => (this.corpus!.isPublic = isPublic));
  }

  previousState(): void {
    window.history.back();
  }
}
