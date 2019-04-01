import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductosService } from 'src/app/services/productos.service';
import { Producto } from 'src/app/models/Producto';
import { Categoria } from 'src/app/models/Categoria';
import { map, catchError } from 'rxjs/operators';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent implements OnInit {

  producto: Producto;

  constructor( private activatedRoute: ActivatedRoute, private router: Router,
               private productosService: ProductosService
     ) {

    this.activatedRoute.params.subscribe( params => {
      console.log( params.id );
      this.productosService
        .getProducto(params.id).subscribe((p: Producto) => this.producto = p);
      console.log(this.producto);
    });
   }

  ngOnInit() {

  }

  comprar( idx: number ) {
    console.log('comprar ' + idx);
    this.router.navigate( ['/comprar', idx] );
  }
}
