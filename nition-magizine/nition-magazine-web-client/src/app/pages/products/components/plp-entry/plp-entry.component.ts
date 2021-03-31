import { Component, Input, OnInit } from '@angular/core';
import { getCurrencySymbol } from '@angular/common';
import { Router } from '@angular/router';

import { ProductModel } from '../../models';
import { ProductStateService} from '../../services';
import { routes } from '../../../../consts';

@Component({
  selector: 'app-plp-entry',
  templateUrl: './plp-entry.component.html',
  styleUrls: ['./plp-entry.component.scss']
})
export class PlpEntryComponent implements OnInit {

  public routers: typeof routes = routes;

  @Input() product: ProductModel;

  currencySymbol: string;
  roundPrice: string;
  centsPrice: string;

  constructor(private productStateService: ProductStateService, private router: Router) { }

  ngOnInit(): void {
    this.currencySymbol = getCurrencySymbol('UAH', 'narrow');
    [this.roundPrice, this.centsPrice] = `${(this.product.price).toFixed(2)}`.split('.');
  }

  navigateToDetails(): void {
    this.productStateService.setProductId$(this.product.id);
    this.router.navigate([this.routers.PRODUCTS_DETAILS]);
  }
}
