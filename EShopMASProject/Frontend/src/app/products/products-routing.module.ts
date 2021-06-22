import { NgModule } from '@angular/core';
import {  RouterModule, Routes } from '@angular/router';
import { ProductsListCompoent } from './products-list/products-list.component';

const routes: Routes = [
  {
      path:"",
      component: ProductsListCompoent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductsRoutingModule { }