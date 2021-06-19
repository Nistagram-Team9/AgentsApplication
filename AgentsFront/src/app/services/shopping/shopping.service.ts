import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Message } from 'src/app/model/message.model';
import { ShoppingOrder } from 'src/app/model/shopping-order.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  private path = `http://localhost:8080/orders`;

  constructor(private http: HttpClient) { }

  createShoppingOrder(shoppingOrder: ShoppingOrder): Observable<Message> {
    console.log(shoppingOrder.productIds)
    return this.http.post<Message>(this.path, shoppingOrder);
  }

}
