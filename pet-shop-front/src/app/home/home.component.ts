import { Component, OnInit } from '@angular/core';
import { ProductCartItem } from '../products/product-cart-item';
import { Product } from '../model/product';
import { CustomCarItemComponent } from '../custom-car-item/custom-car-item.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {

  }

}
