import { Producto } from './Producto';

export class PageProductos {
  content: Producto[];
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  first: boolean;
  sort: string;
  numberOfElements: number;
}
