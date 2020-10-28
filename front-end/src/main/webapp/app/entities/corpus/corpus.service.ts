import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, SearchWithPagination } from 'app/shared/util/request-util';
import { ICorpus } from 'app/shared/model/corpus.model';

type EntityResponseType = HttpResponse<ICorpus>;
type EntityArrayResponseType = HttpResponse<ICorpus[]>;

@Injectable({ providedIn: 'root' })
export class CorpusService {
  public resourceUrl = SERVER_API_URL + 'api/corpora';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/corpora';

  constructor(protected http: HttpClient) {}

  create(corpus: ICorpus): Observable<EntityResponseType> {
    return this.http.post<ICorpus>(this.resourceUrl, corpus, { observe: 'response' });
  }

  update(corpus: ICorpus): Observable<EntityResponseType> {
    return this.http.put<ICorpus>(this.resourceUrl, corpus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICorpus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICorpus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICorpus[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
