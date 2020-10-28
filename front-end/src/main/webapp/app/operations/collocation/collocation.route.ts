import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { CollocationComponent } from 'app/operations/collocation/collocation.component';
import { CollocationResultComponent } from 'app/operations/collocation/collocation-result/collocation-result.component';

export const COLLOCATION_ROUTE: Routes = [
  {
    path: 'collocation',
    component: CollocationComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'collocation.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'collocation/result',
    component: CollocationResultComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'collocation.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
