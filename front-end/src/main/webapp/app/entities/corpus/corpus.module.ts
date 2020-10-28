import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CorpusManagerSharedModule } from 'app/shared/shared.module';
import { CorpusComponent } from './corpus.component';
import { CorpusDetailComponent } from './corpus-detail.component';
import { CorpusUpdateComponent } from './corpus-update.component';
import { CorpusDeleteDialogComponent } from './corpus-delete-dialog.component';
import { corpusRoute } from './corpus.route';

@NgModule({
  imports: [CorpusManagerSharedModule, RouterModule.forChild(corpusRoute)],
  declarations: [CorpusComponent, CorpusDetailComponent, CorpusUpdateComponent, CorpusDeleteDialogComponent],
  entryComponents: [CorpusDeleteDialogComponent]
})
export class CorpusManagerCorpusModule {}
