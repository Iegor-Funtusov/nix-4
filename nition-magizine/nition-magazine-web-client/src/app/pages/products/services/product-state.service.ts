import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable()
export class ProductStateService {

  private _productId$ = new BehaviorSubject(null);

  getProductId$(): Observable<number> {
    return this._productId$.asObservable();
  }

  setProductId$(value: number) {
    this._productId$.next(value);
  }
}
