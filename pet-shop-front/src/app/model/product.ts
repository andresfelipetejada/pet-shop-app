import { AuditModel } from './audit-model';
import { Category } from './category';

export class Product extends AuditModel {
    id?: string;
    name: string;
    sku: string;
    description: string;
    price: number;
    stock: number;
    image?: string;
    category: Category;
}
