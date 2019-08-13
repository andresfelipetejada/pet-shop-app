import { Component, OnInit } from '@angular/core';
import { ShowcaseItem, CartItem } from 'ng-shopping-cart';
import { ProductCartItem } from '../products/product-cart-item';

@Component({
  selector: 'app-custom-car-item',
  templateUrl: './custom-car-item.component.html',
  styleUrls: ['./custom-car-item.component.scss']
})
export class CustomCarItemComponent implements ShowcaseItem  {
  format: string;
  item: ProductCartItem;
}
