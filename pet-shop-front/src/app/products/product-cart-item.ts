import { BaseCartItem } from 'ng-shopping-cart';
import { Product } from '../model/product';

export class ProductCartItem extends BaseCartItem {

    constructor(product: Product) {
        super();
        this.id = product.id;
        this.name = product.name;
        this.price = product.price;
        this.image = product.image;
    }

    getId() {
        return this.id;
    }
    getName(): string {
        return this.name;
    }
    getPrice(): number {
        return this.price;
    }

    getImage(): string {
        return this.image || './assets/images/noimagepet.png';
    }

}
