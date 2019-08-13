import { Component, OnInit } from '@angular/core';
import { CartViewDisplay, CartService } from 'ng-shopping-cart';
import { ProductCartItem } from '../products/product-cart-item';

@Component({
  selector: 'app-custom-cart-view',
  templateUrl: './custom-cart-view.component.html',
  styleUrls: ['./custom-cart-view.component.scss']
})
export class CustomCartViewComponent implements OnInit {

  tax = 0;
  shipping = 0;
  showImages = true;
  useCustom = true;

  constructor(public cartService: CartService<ProductCartItem>) {

  }

  ngOnInit(): void {
    this.tax = this.cartService.getTaxRate();
    this.shipping = this.cartService.getShipping();
  }

  setTaxRate() {
    const val = parseFloat(this.tax.toString());
    this.cartService.setTaxRate(val);
    this.tax = this.cartService.getTaxRate();
  }

  setShipping() {
    const val = parseFloat(this.shipping.toString());
    this.cartService.setShipping(val);
    this.shipping = this.cartService.getShipping();
  }

}
