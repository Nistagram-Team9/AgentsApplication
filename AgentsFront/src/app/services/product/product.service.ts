import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from 'src/app/model/product.model';
import { Message } from 'src/app/model/message.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  private path = `http://localhost:8080/products`;

  constructor(private http: HttpClient) { }

  createProduct(name: string, price: string, total: string, picture: File): Observable<Message> {
    const data: FormData = new FormData();
    data.append('name', name);
    data.append('price', price);
    data.append('total', total);
    data.append('file', picture);
    return this.http.post<Message>(this.path, data);
  }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.path);
  }

  delete(id: string): Observable<Message> {
    return this.http.delete<Message>(this.path+'/'+id);
  }

  update(id: string, name: string, price: string, total: string, picture: File): Observable<Message> {
    const data: FormData = new FormData();
    data.append('name', name);
    data.append('price', price);
    data.append('total', total);
    data.append('file', picture);
    return this.http.put<Message>(this.path+'/'+id, data);
  }

}
