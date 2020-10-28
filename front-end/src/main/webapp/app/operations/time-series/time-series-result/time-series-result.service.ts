import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITimeSeriesListResult } from 'app/shared/model/time-series-result.model';

type EntityResponseType = HttpResponse<ITimeSeriesListResult>;
type EntityArrayResponseType = HttpResponse<ITimeSeriesListResult[]>;

@Injectable({ providedIn: 'root' })
export class TimeSeriesResultService {
  public resourceUrl = SERVER_API_URL + 'api/time-series/result';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITimeSeriesListResult>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
