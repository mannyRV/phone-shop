import { Component,  Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent {
  products = [
    {
      id: 1,
      name: 'Iphone 13 Pro Max',
      price: 1000,
      description: 'New 13 pro max in the Box',
      isAvailable: true,
      imagePath: 'assets/iphone-13-pro-max.jpg',
      makeDate: Date.now(),
      discountPrice: 10000
    },
    {
      id: 2,
      name: 'Samsung Galaxy S21 Ultra',
      price: 1000,
      description: 'New S21 Ultra in the Box',
      isAvailable: true,
      imagePath: 'assets/sg-s21-ultra.jpg',
      makeDate: Date.now()
    }
  ]

  @Output()
  buy = new EventEmitter();

  addToCart(event: any) {
    this.buy.emit(event)
  }

}
