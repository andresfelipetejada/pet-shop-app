import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CustomCartViewComponent } from './custom-cart-view/custom-cart-view.component';
import { AdminCategoryComponent } from './admin/admin-category/admin-category.component';
import { AdminProductComponent } from './admin/admin-product/admin-product.component';
import { AdminClientComponent } from './admin/admin-client/admin-client.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'car-shop',
    component: CustomCartViewComponent
  },
  {
    path: 'admin/category',
    component: AdminCategoryComponent
  },
  {
    path: 'admin/product',
    component: AdminProductComponent
  },
  {
    path: 'admin/client',
    component: AdminClientComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
