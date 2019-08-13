import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';

import { Product } from 'src/app/model/product';
import { GenericService } from 'src/app/service/generic.service';
import { Category } from 'src/app/model/category';

@Component({
  selector: 'app-admin-product',
  templateUrl: './admin-product.component.html',
  styleUrls: ['./admin-product.component.scss']
})
export class AdminProductComponent implements OnInit {
  displayedColumns: string[] = ['category', 'name', 'sku', 'description', 'price', 'stock', 'image', 'edit', 'delete'];
  dataSource = new MatTableDataSource<any>();

  selectedProduct: Product = new Product();
  loading = false;

  myControl = new FormControl();
  catogories: Category[];

  constructor(public service: GenericService) {
  }

  ngOnInit() {
    this.refresh();
    this.loadCategory();
  }

  async loadCategory() {
    this.loading = true;
    this.catogories = await this.service.getAll('category');
    this.loading = false;
  }

  displayFn(category: Category) {
    if (category) { return category.name; }
  }

  async refresh() {
    this.loading = true;
    const data = await this.service.getAll('product');
    this.dataSource.data = data;
    this.loading = false;
  }

  async updateProduct() {
    if (this.selectedProduct.id !== undefined) {
      await this.service.update('product', this.selectedProduct.id, this.selectedProduct);
    } else {
      await this.service.createRelation('product', this.selectedProduct.category.id, this.selectedProduct);
    }
    this.selectedProduct = new Product();
    await this.refresh();
  }

  editProduct(product: Product) {
    this.selectedProduct = product;
  }

  clearProduct() {
    this.selectedProduct = new Product();
  }

  async deleteProduct(product: Product) {
    this.loading = true;
    if (confirm(`Are you sure you want to delete the product ${product.name}. This cannot be undone.`)) {
      this.service.delete('product', product.id);
    }
    await this.refresh();
  }
}
