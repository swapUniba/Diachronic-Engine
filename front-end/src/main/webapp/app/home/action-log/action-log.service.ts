import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, SearchWithPagination } from 'app/shared/util/request-util';
import { IActionLog } from 'app/shared/model/action-log.model';

type EntityResponseType = HttpResponse<IActionLog>;
type EntityArrayResponseType = HttpResponse<IActionLog[]>;

@Injectable({ providedIn: 'root' })
export class ActionLogService {
  public resourceUrl = SERVER_API_URL + 'api/action-logs';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/action-logs';

  constructor(protected http: HttpClient) {}

  create(actionLog: IActionLog): Observable<EntityResponseType> {
    return this.http.post<IActionLog>(this.resourceUrl, actionLog, { observe: 'response' });
  }

  update(actionLog: IActionLog): Observable<EntityResponseType> {
    return this.http.put<IActionLog>(this.resourceUrl, actionLog, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IActionLog>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IActionLog[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IActionLog[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
