import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ConcordanceComponent } from 'app/operations/concordance/concordance.component';
import { ConcordanceResultComponent } from 'app/operations/concordance/concordance-result/concordance-result.component';

export const CONCORDANCE_ROUTE: Routes = [
  {
    path: 'concordance',
    component: ConcordanceComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'concordance.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'concordance/result',
    component: ConcordanceResultComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'concordance.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
