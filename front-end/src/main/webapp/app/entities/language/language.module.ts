import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CorpusManagerSharedModule } from 'app/shared/shared.module';
import { LanguageComponent } from './language.component';
import { LanguageDetailComponent } from './language-detail.component';
import { LanguageUpdateComponent } from './language-update.component';
import { LanguageDeleteDialogComponent } from './language-delete-dialog.component';
import { languageRoute } from './language.route';

@NgModule({
  imports: [CorpusManagerSharedModule, RouterModule.forChild(languageRoute)],
  declarations: [LanguageComponent, LanguageDetailComponent, LanguageUpdateComponent, LanguageDeleteDialogComponent],
  entryComponents: [LanguageDeleteDialogComponent]
})
export class CorpusManagerLanguageModule {}
