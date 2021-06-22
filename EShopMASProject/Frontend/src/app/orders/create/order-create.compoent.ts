import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { CreateOrderRequest } from "src/app/DTO's/request/create-order.request";
import { PaymentTypeEnum } from "src/app/enums/PaymentTypeEnum";
import { Person } from "src/app/models/Person.model";
import { StorageProduct } from "src/app/models/StorageProduct";
import { environment } from "src/environments/environment";
import { OrdersService } from "../orders.service";

@Component({
    selector: 'orders-create',
    templateUrl: './order-create.compoent.html',
    styleUrls: ['./order-create.compoent.css']
})

export class OrderCreateComponent implements OnInit{
    
    public orderForm: FormGroup;
    private user: Person;
    private shoopingCart: StorageProduct[];

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private ordersService: OrdersService
    ) {}

    ngOnInit(): void {
        if(!localStorage.getItem('cart'))
            this.router.navigateByUrl('/');

        this.shoopingCart = JSON.parse(localStorage.getItem('cart'));
        this.user = JSON.parse(localStorage.getItem('user'));

        this.orderForm = this.formBuilder.group({
            recipientName: new FormControl(this.user.name, [Validators.required]),
            recipientSurname: new FormControl(this.user.surname, [Validators.required]),
            recipientEmail: new FormControl(this.user.email, [Validators.required, Validators.email]),
            recipientPhoneNumber: new FormControl(this.user.phoneNumber, [Validators.required, Validators.maxLength(15)]),
            recipientAddress: new FormControl("", [Validators.required]),
            deliveryType: new FormControl("", [Validators.required]),
            paymentType: new FormControl("", [Validators.required])
        });
    }

    onDeliveryTypeSelect($event: number): void {
        this.orderForm.patchValue({
            recipientAddress: ""
        });
    }

    showWarningStyles(controlName: string): boolean {
        if(this.orderForm.get(controlName)){
            return this.orderForm.get(controlName).touched && !this.orderForm.get(controlName).valid;
        }

        return false;
    }

    onSubmit(): void {
        if(this.orderForm.valid) {
            let request: CreateOrderRequest = {
                clientId: this.user.client.clientId,
                recipientName: this.orderForm.value.recipientName,
                recipientSurname: this.orderForm.value.recipientSurname,
                recipientPhoneNumber: this.orderForm.value.recipientPhoneNumber,
                recipientEmail: this.orderForm.value.recipientEmail,
                deliveryAddress: this.orderForm.value.recipientAddress,
                paymentType: this.orderForm.value.paymentType,
                deliveryType: this.orderForm.value.deliveryType,
                products: this.shoopingCart.map(el => {
                    return {productId: el.productId, amount: el.amount};
                })
            } 
            if((request.paymentType == PaymentTypeEnum.OnlinePayment && environment.passOnlinePayment) || request.paymentType != PaymentTypeEnum.OnlinePayment) {
                this.ordersService.createOrder(request)
                 .subscribe(data => {
                    localStorage.removeItem('cart');
                    this.router.navigate(['finished'], {state: {finishedOrder: true, orderId: data, isOnlinePayment: request.paymentType == PaymentTypeEnum.OnlinePayment},relativeTo: this.activatedRoute});
                 });
            } else {
                localStorage.removeItem('cart');
                this.router.navigate(['finished'], {state: { finishedOrder: true, isOnlinePayment: true}, relativeTo: this.activatedRoute});
            }
            

        } else {
            this.orderForm.markAllAsTouched();
        }
    }
    
}