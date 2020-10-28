import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICollocationResult } from 'app/shared/model/collocation-result.model';

type EntityResponseType = HttpResponse<ICollocationResult>;
type EntityArrayResponseType = HttpResponse<ICollocationResult[]>;

@Injectable({ providedIn: 'root' })
export class CollocationResultService {
  public resourceUrl = SERVER_API_URL + 'api/collocation/result';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICollocationResult[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
