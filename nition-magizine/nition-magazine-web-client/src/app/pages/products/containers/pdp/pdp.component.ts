import { ChangeDetectionStrategy, Component, OnDestroy, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductService, ProductStateService } from '../../services';
import { ProductModel } from '../../models';

@Component({
  selector: 'app-pdp',
  templateUrl: './pdp.component.html',
  styleUrls: ['./pdp.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PdpComponent implements OnInit, OnDestroy {

  productData: Observable<ProductModel>;

  constructor(private productStateService: ProductStateService, private productService: ProductService) { }

  ngOnInit(): void {
    this.productStateService.getProductId$().subscribe(id => {
      if (id) {
        this.productData = this.productService.loadProduct(id);
      }
    });
  }

  ngOnDestroy(): void {
  }
}
