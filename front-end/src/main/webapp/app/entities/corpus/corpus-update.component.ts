import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { Corpus, ICorpus } from 'app/shared/model/corpus.model';
import { CorpusService } from './corpus.service';
import { ILanguage } from 'app/shared/model/language.model';
import { LanguageService } from 'app/entities/language/language.service';

@Component({
  selector: 'jhi-corpus-update',
  templateUrl: './corpus-update.component.html'
})
export class CorpusUpdateComponent implements OnInit {
  isSaving = false;
  languages: ILanguage[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(255)]],
    periodStart: [null, [Validators.min(0), Validators.max(9999)]],
    periodEnd: [null, [Validators.min(0), Validators.max(9999)]],
    isPublic: [],
    languageId: []
  });

  constructor(
    protected corpusService: CorpusService,
    protected languageService: LanguageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ corpus }) => {
      this.updateForm(corpus);
      this.languageService.getAll().subscribe((res: HttpResponse<ILanguage[]>) => (this.languages = res.body || []));
    });
  }

  updateForm(corpus: ICorpus): void {
    this.editForm.patchValue({
      id: corpus.id,
      name: corpus.name,
      periodStart: corpus.periodStart,
      periodEnd: corpus.periodEnd,
      isPublic: corpus.isPublic,
      languageId: corpus.languageId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const corpus = this.createFromForm();
    if (corpus.id !== undefined) {
      this.subscribeToSaveResponse(this.corpusService.update(corpus));
    } else {
      this.subscribeToSaveResponse(this.corpusService.create(corpus));
    }
  }

  trackById(index: number, item: ILanguage): any {
    return item.id;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICorpus>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  private createFromForm(): ICorpus {
    return {
      ...new Corpus(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      periodStart: this.editForm.get(['periodStart'])!.value,
      periodEnd: this.editForm.get(['periodEnd'])!.value,
      isPublic: !!this.editForm.get(['isPublic'])!.value,
      languageId: this.editForm.get(['languageId'])!.value
    };
  }
}
