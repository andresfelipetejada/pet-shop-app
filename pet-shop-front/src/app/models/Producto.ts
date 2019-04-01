import { Categoria } from './Categoria';

export class Producto {
  createdAt: Date;
  updatedAt: Date;
  id: number;
  nombre: string;
  descripcion: string;
  ficha: string;
  urlImagen: string;
  precio: number;
  cantidad: number;
  categoria: Categoria;
}
