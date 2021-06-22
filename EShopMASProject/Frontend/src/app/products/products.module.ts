import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ProductsListCompoent } from './products-list/products-list.component';
import { ProductsRoutingModule } from './products-routing.module';
import { ProductsService } from './products.service';


@NgModule({
  declarations: [
      ProductsListCompoent
  ],
  imports: [
    CommonModule,
    ProductsRoutingModule
  ],
  providers: [
      ProductsService
  ],
})
export class ProductsModule { }
