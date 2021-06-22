import { NgModule } from '@angular/core';
import {  RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../authGuard';
import { OrderCreateComponent } from './create/order-create.compoent';
import { OrderFinishedComponent } from './finished/order-finished.component';
import { OrdersListComponent } from './list/orders-list.component';

const routes: Routes = [
  {
    path:"",
    component: OrdersListComponent,
    canActivate: [AuthGuard]
  },
  {
      path: "create",
      canActivate: [AuthGuard],
      children: [
        {
          path: "",
          component: OrderCreateComponent,
        },
        {
          path: "finished",
          component: OrderFinishedComponent,
        }
      ]
  },
 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  providers: [AuthGuard],
  exports: [RouterModule]
})
export class OrdersRoutingModule { }