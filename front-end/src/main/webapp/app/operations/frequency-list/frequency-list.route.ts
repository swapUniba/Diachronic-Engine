import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { FrequencyListComponent } from 'app/operations/frequency-list/frequency-list.component';
import { FrequencyListResultComponent } from 'app/operations/frequency-list/frequency-list-result/frequency-list-result.component';

export const FREQUENCY_LIST_ROUTE: Routes = [
  {
    path: 'frequency-list',
    component: FrequencyListComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'frequency-list.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'frequency-list/result',
    component: FrequencyListResultComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'frequency-list.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
