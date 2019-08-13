import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import {
  MatButtonModule,
  MatDividerModule,
  MatIconModule,
  MatMenuModule,
  MatProgressSpinnerModule,
  MatTableModule,
  MatToolbarModule,
  MatCardModule,
  MatTabsModule,
  MatListModule,
  MatSidenavModule,
  MatAutocompleteModule,
  MatFormFieldModule,
  MatInputModule,
  MatDialogModule
} from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import {ShoppingCartModule} from 'ng-shopping-cart';
import { ProductCartItem } from './products/product-cart-item';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { CustomCarItemComponent } from './custom-car-item/custom-car-item.component';
import { CustomCartViewComponent } from './custom-cart-view/custom-cart-view.component';
import { CustomCarCheckoutComponent } from './custom-car-checkout/custom-car-checkout.component';
import { AdminComponent } from './admin/admin.component';
import { AdminClientComponent } from './admin/admin-client/admin-client.component';
import { AdminInvoiceComponent } from './admin/admin-invoice/admin-invoice.component';
import { AdminProductComponent } from './admin/admin-product/admin-product.component';
import { AdminCategoryComponent } from './admin/admin-category/admin-category.component';
import { CatalogComponent } from './catalog/catalog.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CustomCarItemComponent,
    CustomCartViewComponent,
    CustomCarCheckoutComponent,
    AdminComponent,
    AdminClientComponent,
    AdminInvoiceComponent,
    AdminProductComponent,
    AdminCategoryComponent,
    CatalogComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FlexLayoutModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatDividerModule,
    MatProgressSpinnerModule,
    MatTabsModule,
    MatListModule,
    MatSidenavModule,
    MatCardModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    ShoppingCartModule.forRoot({ // <-- Add the cart module to your root module
      itemType: ProductCartItem, // <-- Configuration is optional
      serviceType: 'localStorage',
      serviceOptions: {
        storageKey: 'NgShoppingCart',
        clearOnError: true
      }
    })
  ],
  providers: [],
  entryComponents: [CustomCarItemComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
