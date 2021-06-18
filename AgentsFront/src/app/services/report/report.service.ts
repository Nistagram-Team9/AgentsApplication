import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Product } from 'src/app/model/product.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  private path = `http://localhost:8080/reports`;

  constructor(private http: HttpClient) { }

  mostSold(): Observable<Product[]> {
    return this.http.get<Product[]>(this.path+'/mostSold');
  }

  mostEarned(): Observable<Product[]> {
    return this.http.get<Product[]>(this.path+'/mostEarned');
  }


}
