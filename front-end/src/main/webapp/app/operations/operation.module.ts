import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FrequencyListComponent } from '../operations/frequency-list/frequency-list.component';
import { FREQUENCY_LIST_ROUTE } from 'app/operations/frequency-list/frequency-list.route';
import { CollocationComponent } from '../operations/collocation/collocation.component';
import { COLLOCATION_ROUTE } from 'app/operations/collocation/collocation.route';
import { TimeSeriesComponent } from '../operations/time-series/time-series.component';
import { TIME_SERIES_ROUTE } from 'app/operations/time-series/time-series.route';
import { ConcordanceComponent } from '../operations/concordance/concordance.component';
import { CONCORDANCE_ROUTE } from 'app/operations/concordance/concordance.route';
import { CollocationSearchComponent } from './collocation/collocation-search/collocation-search.component';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { CommonModule } from '@angular/common';
import { NgJhipsterModule } from 'ng-jhipster';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { CollocationResultComponent } from './collocation/collocation-result/collocation-result.component';
import { MatTableModule } from '@angular/material/table';
import { CorpusManagerSharedModule } from 'app/shared/shared.module';
import { ConcordanceSearchComponent } from './concordance/concordance-search/concordance-search.component';
import { ConcordanceResultComponent } from './concordance/concordance-result/concordance-result.component';
import { MatIconModule } from '@angular/material/icon';
import { FrequencyListSearchComponent } from './frequency-list/frequency-list-search/frequency-list-search.component';
import { FrequencyListResultComponent } from './frequency-list/frequency-list-result/frequency-list-result.component';
import { MatListModule } from '@angular/material/list';
import { MatRadioModule } from '@angular/material/radio';
import { TimeSeriesSearchComponent } from './time-series/time-series-search/time-series-search.component';
import { TimeSeriesResultComponent } from './time-series/time-series-result/time-series-result.component';
import { NgxEchartsModule } from 'ngx-echarts';
import { ClipboardModule } from 'ngx-clipboard';
import { MatTabsModule } from '@angular/material/tabs';
import { NgxLoadingModule } from 'ngx-loading';
import { TimeSeriesSearchSimilarityComponent } from './time-series/time-series-search-similarity/time-series-search-similarity.component';

@NgModule({
  imports: [
    RouterModule.forChild([...FREQUENCY_LIST_ROUTE, ...COLLOCATION_ROUTE, ...TIME_SERIES_ROUTE, ...CONCORDANCE_ROUTE]),
    MatCardModule,
    ReactiveFormsModule,
    TranslateModule,
    CommonModule,
    NgJhipsterModule,
    MatButtonModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatInputModule,
    MatTableModule,
    CorpusManagerSharedModule,
    MatIconModule,
    MatListModule,
    MatRadioModule,
    NgxEchartsModule,
    ClipboardModule,
    MatTabsModule,
    NgxLoadingModule.forRoot({})
  ],
  declarations: [
    FrequencyListComponent,
    CollocationComponent,
    TimeSeriesComponent,
    ConcordanceComponent,
    CollocationSearchComponent,
    CollocationResultComponent,
    ConcordanceSearchComponent,
    ConcordanceResultComponent,
    FrequencyListSearchComponent,
    FrequencyListResultComponent,
    TimeSeriesSearchComponent,
    TimeSeriesResultComponent,
    TimeSeriesSearchSimilarityComponent
  ],
  exports: [ NgxLoadingModule ]
})
export class CorpusManagerOperationModule {}
