import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard/dashboard.component';
import { CreateProductComponent } from './product/create-product/create-product.component';
import { AllProductsComponent } from './product/all-products/all-products.component';
import { UpdateProductComponent } from './product/update-product/update-product.component';


const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      { path: 'create-product', component: CreateProductComponent },
      { path: 'all-products', component: AllProductsComponent },
      { path: 'update-product/:id', component: UpdateProductComponent }
    ]
  },
  // {
  //   path: 'create-product',
  //   component: CreateProductComponent
  // },
  { path: '**', redirectTo: 'dashboard' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
