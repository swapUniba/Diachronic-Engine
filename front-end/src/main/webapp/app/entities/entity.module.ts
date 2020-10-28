import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'corpus',
        loadChildren: () => import('./corpus/corpus.module').then(m => m.CorpusManagerCorpusModule)
      },
      {
        path: 'language',
        loadChildren: () => import('./language/language.module').then(m => m.CorpusManagerLanguageModule)
      }
    ])
  ]
})
export class CorpusManagerEntityModule {}
