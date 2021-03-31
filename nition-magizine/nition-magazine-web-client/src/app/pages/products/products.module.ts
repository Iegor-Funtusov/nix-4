import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { EllipsisModule } from 'ngx-ellipsis';

import { ProductsRoutingModule } from './products-routing.module';
import { PdpComponent, PlpComponent } from './containers';
import { ProductService, ProductStateService } from './services';
import { PlpEntryComponent } from './components';

@NgModule({
  declarations: [
    PdpComponent,
    PlpComponent,
    PlpEntryComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    EllipsisModule,
    ProductsRoutingModule
  ],
  providers: [
    ProductService,
    ProductStateService
  ]
})
export class ProductsModule { }
