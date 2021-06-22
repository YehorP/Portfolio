import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";
import { Product } from "../models/Product";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})

export class ProductsService {

    constructor(
        private http: HttpClient
      ) { }

    getAll(): Observable<{products: Product[]}> {
        return this.http.get<{products: Product[]}>(`${environment.url}/products`);
    }
  }