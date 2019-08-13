import { ProductCartItem } from './product-cart-item';
import { Product } from '../model/product';

describe('ProductCartItem', () => {
  it('should create an instance', () => {
    expect(new ProductCartItem(new Product())).toBeTruthy();
  });
});
