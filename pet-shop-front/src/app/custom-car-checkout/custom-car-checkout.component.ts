import { Component, OnInit, EventEmitter, OnDestroy } from '@angular/core';
import { CheckoutType, CheckoutHttpSettings, CheckoutPaypalSettings, CartService } from 'ng-shopping-cart';
import { ProductCartItem } from '../products/product-cart-item';
import { Subscription } from 'rxjs';
import { Client } from '../model/client';
import { environment } from './../../environments/environment';
import { GenericService } from '../service/generic.service';

@Component({
  selector: 'app-custom-car-checkout',
  templateUrl: './custom-car-checkout.component.html',
  styleUrls: ['./custom-car-checkout.component.scss']
})
export class CustomCarCheckoutComponent  implements OnInit, OnDestroy {
  private serviceSubscription: Subscription;
  custom = false;
  label = 'Comprar';
  service: CheckoutType = 'http';
  settingsCollapsed = false;
  resultsCollapsed = false;
  disabled = false;
  paypalConfig: CheckoutPaypalSettings = {business: '', itemName: '', itemNumber: '', serviceName: 'NgShoppingCart', country: 'US'};
  client: Client;
  httpConfig: CheckoutHttpSettings = {url: '', method: 'POST'};

  findcedula: string;
  loading = false;

  constructor(private cartService: CartService<ProductCartItem>, private genericService: GenericService) {
    this.httpConfig.body = this.client;
  }

  ngOnInit(): void {
    this.checkService();
    this.serviceSubscription = this.cartService.onItemsChanged.subscribe(() => {
      this.checkService();
    });
  }

  checkService() {
    this.disabled = this.cartService.isEmpty();
  }

  onSuccess(data: any) {
    console.log('Checkout successful');
    console.error(data);
  }

  onError(err: any) {
    console.log('An http error was received');
    console.dir(err);
  }

  ngOnDestroy(): void {
    this.serviceSubscription.unsubscribe();
  }

  async findClient() {
    if (this.findcedula && this.findcedula.trim().length > 0 ) {
      this.loading = true;
      const data = await this.genericService.get('client', this.findcedula);
      this.client = data;
      if ( this.client ) {
        this.httpConfig.url = `${environment.baseApiUrl}/factura/${this.findcedula}`;
      }
      this.loading = false;
    }
  }
}
