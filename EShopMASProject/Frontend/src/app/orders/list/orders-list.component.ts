import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { DeliveryTypeEnum } from "src/app/enums/DeliveryTypeEnum";
import { PaymentTypeEnum } from "src/app/enums/PaymentTypeEnum";
import { RealizationStateEnum } from "src/app/enums/RealizationStateEnum";
import { Order } from "src/app/models/Order.model";
import { Person } from "src/app/models/Person.model";
import { OrdersService } from "../orders.service";

@Component({
    selector: 'orders-list',
    templateUrl: './orders-list.component.html',
    styleUrls: ['./orders-list.component.css']
})

export class OrdersListComponent implements OnInit{

    private user: Person;
    public orders: any[];

    constructor(
        private ordersService: OrdersService,
        private router: Router
    ) {}

    ngOnInit(): void {
        this.user = JSON.parse(localStorage.getItem('user'));
        this.ordersService
         .getOrdersByUserId(this.user.client.clientId)
         .subscribe(data => {
            this.orders = data;
         });
    }

    calculateTotalPrice(order): number {
        let totalPrice = 0;
        for(let productOrder of order.products) {
            totalPrice+= productOrder.amount * productOrder.product.pricePerOne;
        }

        return totalPrice;
    }

    getDeliveryMethodName(index: number): string {
        return DeliveryTypeEnum[index];
    }

    getPaymentMethodName(index: number): string {
        return PaymentTypeEnum[index];
    }

    getRealizationState(index: number): string {
        return RealizationStateEnum[index];
    }

    getCreationDate(creationDate: string): Date {
        return new Date(creationDate);
    }

    getFinishedDate(finishedDate: string) {
        return finishedDate == null ? "Not finished yet" : new Date(finishedDate);
    }
}