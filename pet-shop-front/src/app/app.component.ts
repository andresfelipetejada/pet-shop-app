import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'pet-shop-front';

  routes = [
    { path: '/', name: 'Home' },
    { path: 'admin/category', name: 'Categorías' },
    { path: 'admin/product', name: 'Productos' },
    { path: 'admin/client', name: 'Clientes' },
    { path: 'admin/invoice', name: 'Facturacion' }
  ];

  public isAuthenticated: boolean;

  icon = '';
  noItemsText = 'No items';
  oneItemText = 'Un item';
  manyItemsText = '# items';

  constructor() {
    this.isAuthenticated = false;
  }

  login() {
  }

  logout() {
  }
}
