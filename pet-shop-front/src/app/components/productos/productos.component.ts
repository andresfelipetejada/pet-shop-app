import { Component, OnInit } from '@angular/core';
import { Producto } from 'src/app/models/Producto';
import { PageProductos } from 'src/app/models/PageProductos';
import { ProductosService } from 'src/app/services/productos.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {

  productos: Producto[];
  slides: any = [[]];
  pageProductos: PageProductos;
  selectedPage = 0;

  constructor(private productoService: ProductosService, private router: Router) {

  }

  ngOnInit() {
    this.getPageProductos(1, 0);
  }

  getProducto(): void {
    this.productoService.getAll()
        .subscribe(productos => this.productos = productos);
  }

  getPageProductos(categoria: number, page: number): void {
    this.productoService.getByCategoriaPage(categoria, page)
        .subscribe(pageproductos => {
          this.productos = pageproductos.content;
          this.slides = this.chunk(this.productos, 3);
          return pageproductos;
        });
  }

  chunk(arr: Producto[], chunkSize: number) {
    const R = [];
    for (let i = 0, len = arr.length; i < len; i += chunkSize) {
      R.push(arr.slice(i, i + chunkSize));
    }
    return R;
  }

  onSelect(page: number): void {
    console.log('selected page : ' + page);
    this.selectedPage = page;
    this.getPageProductos(1, page);
  }

  verDetalle( idx: number ) {
    console.log('verdetalle ' + idx);
    this.router.navigate( ['/producto', idx] );
  }

}
