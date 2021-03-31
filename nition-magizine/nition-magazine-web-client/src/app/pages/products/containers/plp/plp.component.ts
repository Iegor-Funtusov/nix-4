import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';

import { PageDataContainerModel, ProductModel } from '../../models';
import { ProductService } from '../../services';

@Component({
  selector: 'app-plp',
  templateUrl: './plp.component.html',
  styleUrls: ['./plp.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PlpComponent implements OnInit {

  pageData: PageDataContainerModel<ProductModel>;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.loadProducts()
      .subscribe(value => {
        this.pageData = value;
    });
  }
}
