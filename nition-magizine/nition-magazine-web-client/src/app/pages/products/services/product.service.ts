import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '../../../../environments/environment';
import { apipaths } from '../../../consts';
import { DataContainerModel, PageDataContainerModel, ProductModel } from '../models';

@Injectable()
export class ProductService {

  private apiPaths: typeof apipaths = apipaths;
  private env: typeof environment = environment;
  private apiUrl = this.env.api_url;
  private productPath = this.apiPaths.PRODUCTS;

  constructor(private http: HttpClient) { }

  loadProducts(): Observable<PageDataContainerModel<ProductModel>> {
    const httpOptions = {
      headers: new HttpHeaders({})
    };
    const url = this.apiUrl + this.productPath;
    return this.http.get(url, httpOptions)
      .pipe(
        map((res: any) => {
          return res.data;
        }));
  }

  loadProduct(id: number): Observable<ProductModel> {
    const httpOptions = {
      headers: new HttpHeaders({})
    };
    const url = this.apiUrl + this.productPath + '/' + id;
    return this.http.get(url, httpOptions)
      .pipe(
        map((res: any) => {
          console.log(res);
          return res.data;
        }));
  }
}
