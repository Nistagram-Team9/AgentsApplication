import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard/dashboard.component';
import { CreateProductComponent } from './product/create-product/create-product.component';
import { AllProductsComponent } from './product/all-products/all-products.component';
import { UpdateProductComponent } from './product/update-product/update-product.component';
import { ReportComponent } from './report/report.component';
import { CreateOrderComponent } from './orders/create-order/create-order.component';


const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      { path: 'create-product', component: CreateProductComponent },
      { path: 'all-products', component: AllProductsComponent },
      { path: 'update-product/:id', component: UpdateProductComponent },
      { path: 'reports/:option', component: ReportComponent},
      { path: 'shopping-order', component: CreateOrderComponent}
    ]
  },
  { path: '**', redirectTo: 'dashboard' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
