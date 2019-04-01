import { Component, OnInit } from '@angular/core';
import { ProductosService } from 'src/app/services/productos.service';
import { Producto } from 'src/app/models/Producto';
import { PageProductos } from 'src/app/models/PageProductos';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit {

  productos: Producto[];
  slides: any = [[]];
  pageProductos: PageProductos;
  selectedPage = 0;

  constructor(private productoService: ProductosService) {

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

}
