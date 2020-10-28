import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFrequencyListResult } from 'app/shared/model/frequency-list-result.model';

type EntityResponseType = HttpResponse<IFrequencyListResult>;
type EntityArrayResponseType = HttpResponse<IFrequencyListResult[]>;

@Injectable({ providedIn: 'root' })
export class FrequencyListResultService {
  public resourceUrl = SERVER_API_URL + 'api/frequency-list/result';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFrequencyListResult>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
