import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, Router, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { EMPTY, Observable, of } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILanguage, Language } from 'app/shared/model/language.model';
import { LanguageService } from './language.service';
import { LanguageComponent } from './language.component';
import { LanguageDetailComponent } from './language-detail.component';
import { LanguageUpdateComponent } from './language-update.component';

@Injectable({ providedIn: 'root' })
export class LanguageResolve implements Resolve<ILanguage> {
  constructor(private service: LanguageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILanguage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((language: HttpResponse<Language>) => {
          if (language.body) {
            return of(language.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Language());
  }
}

export const languageRoute: Routes = [
  {
    path: '',
    component: LanguageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'corpusManagerApp.language.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LanguageDetailComponent,
    resolve: {
      language: LanguageResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'corpusManagerApp.language.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LanguageUpdateComponent,
    resolve: {
      language: LanguageResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'corpusManagerApp.language.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LanguageUpdateComponent,
    resolve: {
      language: LanguageResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'corpusManagerApp.language.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
