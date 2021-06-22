import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import {  ReactiveFormsModule } from '@angular/forms';
import { OrderCreateComponent } from './create/order-create.compoent';
import { OrderFinishedComponent } from './finished/order-finished.component';
import { OrdersListComponent } from './list/orders-list.component';
import { OrdersRoutingModule } from './orders-routing.module';
import { OrdersService } from './orders.service';

@NgModule({
  declarations: [
    OrderCreateComponent,
    OrderFinishedComponent,
    OrdersListComponent
  ],
  imports: [
    CommonModule,
    OrdersRoutingModule,
    ReactiveFormsModule
  ],
  providers: [
    OrdersService
  ],
})
export class OrdersModule { }