import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { StorageProduct } from "../models/StorageProduct";

@Component({
    selector: 'cart-main',
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.css']
})

export class CartComponent implements OnInit{


    public shoppingCart: StorageProduct[];

    constructor(
        private router: Router
    ) { }

    ngOnInit(): void {
        if(localStorage.getItem('cart')){
            this.shoppingCart = JSON.parse(localStorage.getItem('cart'));
        }
    }

    calcTotalPrice(): number {
        let price = 0;
        for(let product of this.shoppingCart) {
            price += product.pricePerOne * product.amount;
        }
        return price;
    }

    deleteProduct(product): void {
        this.shoppingCart.splice(this.shoppingCart.indexOf(product), 1);
        localStorage.setItem('cart', JSON.stringify(this.shoppingCart));
    }

    productAmountChange(event): void {
        localStorage.setItem('cart', JSON.stringify(this.shoppingCart));
    }

    order(): void {
        if(localStorage.getItem('user')) {
            this.router.navigate(['/orders/create']);
        } else {
            this.router.navigate(['/login'], { state: { navigateTo: '/orders/create' } });
        }
    }
}