import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { CreateOrderRequest } from "../DTO's/request/create-order.request";
import { Order } from "../models/Order.model";

@Injectable({
    providedIn: 'root'
})

export class OrdersService {
    
    constructor(
        private http: HttpClient
    ) {}

    createOrder(request: CreateOrderRequest): Observable<number> {
        return this.http.post<number>(`${environment.url}/orders/create`, request);
    }

    getOrdersByUserId(clientId: number): Observable<Order[]> {
        return this.http.get<Order[]>(`${environment.url}/orders/clientOrders/${clientId}`);
    }
}