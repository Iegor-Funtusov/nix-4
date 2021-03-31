import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PdpComponent, PlpComponent } from './containers';

const routes: Routes = [
  {
    path: '',
    component: PlpComponent
  },
  {
    path: 'details',
    component: PdpComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ProductsRoutingModule { }
