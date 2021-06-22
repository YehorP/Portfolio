import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: "",
    redirectTo: "products",
    pathMatch: "full"
  },
  { 
    path: "products", 
    loadChildren: () => import("./products/products.module").then(m => m.ProductsModule)
  },
  { 
    path: "cart", 
    loadChildren: () => import("./cart/cart.module").then(m => m.CartModule)
  },
  {
    path: "login", 
    loadChildren: () => import("./login/login.module").then(m => m.LoginModule)
  },
  {
    path: "orders", 
    loadChildren: () => import("./orders/orders.module").then(m => m.OrdersModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    preloadingStrategy: PreloadAllModules
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
