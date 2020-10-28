import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { TimeSeriesComponent } from 'app/operations/time-series/time-series.component';
import { TimeSeriesResultComponent } from 'app/operations/time-series/time-series-result/time-series-result.component';

export const TIME_SERIES_ROUTE: Routes = [
  {
    path: 'time-series',
    component: TimeSeriesComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'time-series.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'time-series/result',
    component: TimeSeriesResultComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'time-series.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
