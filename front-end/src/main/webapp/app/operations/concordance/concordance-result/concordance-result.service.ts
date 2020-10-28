import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IConcordanceResult } from 'app/shared/model/concordance-result.model';

type EntityResponseType = HttpResponse<IConcordanceResult>;
type EntityArrayResponseType = HttpResponse<IConcordanceResult[]>;

@Injectable({ providedIn: 'root' })
export class ConcordanceResultService {
  public resourceUrl = SERVER_API_URL + 'api/concordance/result';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConcordanceResult>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
