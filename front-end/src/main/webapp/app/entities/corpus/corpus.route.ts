import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, Router, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { EMPTY, Observable, of } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Corpus, ICorpus } from 'app/shared/model/corpus.model';
import { CorpusService } from './corpus.service';
import { CorpusComponent } from './corpus.component';
import { CorpusDetailComponent } from './corpus-detail.component';
import { CorpusUpdateComponent } from './corpus-update.component';

@Injectable({ providedIn: 'root' })
export class CorpusResolve implements Resolve<ICorpus> {
  constructor(private service: CorpusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICorpus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((corpus: HttpResponse<Corpus>) => {
          if (corpus.body) {
            return of(corpus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Corpus());
  }
}

export const corpusRoute: Routes = [
  {
    path: '',
    component: CorpusComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'corpusManagerApp.corpus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CorpusDetailComponent,
    resolve: {
      corpus: CorpusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'corpusManagerApp.corpus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CorpusUpdateComponent,
    resolve: {
      corpus: CorpusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'corpusManagerApp.corpus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CorpusUpdateComponent,
    resolve: {
      corpus: CorpusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'corpusManagerApp.corpus.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
