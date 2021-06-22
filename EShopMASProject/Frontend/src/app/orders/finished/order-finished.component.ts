import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "src/environments/environment";

@Component({
    selector: 'order-finished',
    templateUrl: './order-finished.component.html',
    styleUrls: ['./order-finished.component.css']
})

export class OrderFinishedComponent implements OnInit{

    isOnlinePayment: boolean;
    isFailedPayment: boolean;
    orderId: number;
    
    constructor(
        private router: Router
    ) {}

    ngOnInit(): void {
        if(!history.state['finishedOrder']) {
            this.router.navigateByUrl('/');
        }
        this.isFailedPayment = environment.passOnlinePayment;
        this.isOnlinePayment = history.state['isOnlinePayment'];
        this.orderId = history.state['orderId'];
    }
}