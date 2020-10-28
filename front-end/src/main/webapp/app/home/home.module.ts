import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CorpusManagerSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ActionLogComponent } from './action-log/action-log.component';

@NgModule({
  imports: [CorpusManagerSharedModule, RouterModule.forChild([HOME_ROUTE]), MatButtonModule, MatIconModule],
  declarations: [HomeComponent, ActionLogComponent]
})
export class CorpusManagerHomeModule {}
