import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private products:Array<any> = []

  productsStream = new BehaviorSubject({
    filter: 'ALL',
    action: '',
    products: this.products
  });

  getProducts(){
    this.httpClient.get("http://localhost:8080/phone-shop/products")
    .subscribe({
      next:(response:any)=>{
        this.products = response;
        this.productsStream.next({
          filter:'All',
          action: 'LOAD_PRODUCTS',
          products: this.products
        })
      }
    })
  }

  addProduct(newProduct:any){
    return new Promise((resolve,reject) => {
      this.httpClient.post("http://localhost:8080/phone-shop/products", {
        name: newProduct.name,
        type: newProduct.type
      }).subscribe({
        next: e => {
          this.products.push(newProduct);
          this.productsStream.next({
            filter:'ALL',
            action: 'ADD_PRODUCT',
            products: this.products
          })
          resolve(true)
        }
      })
    })
  }

  deleteProduct(productId: number) {

    this.httpClient.delete("http://localhost:8080/phone-shop/products/" + productId).subscribe({
      next: e => {
        this.products = this.products.filter(product => product.id !== productId);
        this.productsStream.next({
          filter: 'ALL',
          action: 'DELETE_PRODUCT',
          products: this.products
        })
      }
    });

  }


  constructor(private httpClient: HttpClient) { }
}
