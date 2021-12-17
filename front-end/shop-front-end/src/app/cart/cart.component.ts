import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent {

  cart: any = {}

  constructor(private cartService: CartService) { }

  ngOnInit() {
    this.cart = this.cartService.cart;
  }

}
