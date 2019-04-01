import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { PageProductos } from '../models/PageProductos';
import { catchError, map, tap } from 'rxjs/operators';
import { Producto } from '../models/Producto';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  url = '//localhost:8080/productos';
  urlProducto = '//localhost:8080/producto';

  constructor(private http: HttpClient) {}

  getAll(): Observable<any> {
    return this.http.get<Producto[]>(this.url)
      .pipe(
           catchError(this.handleError('getAll', []))
      );
  }

  getProducto(id: any): any {
    const url = `${this.urlProducto}/${id}`;
    return this.http.get<Producto>(url);
  }

  getByCategoriaPage(categorid: number, page: number): Observable<PageProductos> {
    let url = this.url;
    if (page) {
      url = `${this.url}\\${categorid}\\?page=${page}&size=3`;
    }
    return this.http.get<PageProductos>(url).pipe(
      map(response => {
        const data = response;
        console.log(data.content);
        return data;
      })
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
