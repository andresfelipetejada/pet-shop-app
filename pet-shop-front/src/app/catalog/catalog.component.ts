import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product';
import { ProductCartItem } from '../products/product-cart-item';
import { CustomCarItemComponent } from '../custom-car-item/custom-car-item.component';
import { GenericService } from '../service/generic.service';
import { MatTabChangeEvent } from '@angular/material/tabs';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.scss']
})
export class CatalogComponent implements OnInit {

  selectedProduct: Product = new Product();
  loading = false;

  data: Product[];
  items: ProductCartItem[] = [];
  itemComponent = CustomCarItemComponent;

  constructor(public service: GenericService) {

  }

  ngOnInit() {
    this.refresh();
  }

  async refresh() {
    this.loading = true;
    const data = await this.service.getAll('product');
    this.data = data;

    if (this.data) {
      this.data
      .filter((x) => x.stock > 0)
      .forEach((product) => {
        this.items.push(new ProductCartItem(product));
      });
    }

    this.loading = false;
  }

  tabChanged(tabChangeEvent: MatTabChangeEvent): void {
    const categoria = tabChangeEvent.tab.textLabel;
    if (this.data) {
      this.items = [];
      this.data
      .filter((x) => (x.category.name.toLowerCase() === categoria.toLowerCase()) || categoria.toLowerCase() === 'all')
      .forEach((product) => {
        this.items.push(new ProductCartItem(product));
      });
    }
    console.log('tabChangeEvent => ', tabChangeEvent);
    console.log('index => ', tabChangeEvent.index);
  }

}
