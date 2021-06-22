import { Component, OnInit } from "@angular/core";
import { Product } from "src/app/models/Product";
import { StorageProduct } from "src/app/models/StorageProduct";
import { ProductsService } from "../products.service";

@Component({
    selector: 'products-list',
    templateUrl: './products-list.component.html',
    styleUrls: ['./products-list.component.css']
})

export class ProductsListCompoent implements OnInit{

    public enablePriceSorting: boolean = false;
    public fromSmallerPrice: boolean = true;
    public products: Product[];
    public productsToShow: Product[]
    private shoopingCart: StorageProduct[];

    constructor(private productsService: ProductsService) 
    { }

    ngOnInit(): void {

       this.shoopingCart = localStorage.getItem('cart') ? JSON.parse(localStorage.getItem('cart')) : [];

       this.productsService
        .getAll()
        .subscribe(data => {
            this.products = data.products;
            this.productsToShow = this.products.slice();
        });
    }

    addToCart(element: Product) {
       let cartElement = this.shoopingCart.find(el => el.productId == element.productId);
       if(cartElement) {
           cartElement.amount += 1;
       } else {
           this.shoopingCart.push({
               productId: element.productId,
               name: element.name,
               amount: 1,
               pricePerOne: element.pricePerOne
           });
       }

       localStorage.setItem('cart', JSON.stringify(this.shoopingCart));
    }

    
    changeFiltering() {
        this.enablePriceSorting = true;
        this.fromSmallerPrice = !this.fromSmallerPrice;
        this.sortElements();
    }

    private sortElements() {
        if(this.enablePriceSorting) {
            if(this.fromSmallerPrice){
                //this.products.sort((a,b)=> a.pricePerOne - b.pricePerOne);
                this.productsToShow.sort((a,b)=> a.pricePerOne - b.pricePerOne);
            } else {
                 //this.products.sort((a,b)=> b.pricePerOne - a.pricePerOne).slice();
                 this.productsToShow.sort((a,b)=> b.pricePerOne - a.pricePerOne).slice();
            }
        }
    }

    onSearch($event) {
        if($event.target.value == "") {
            this.productsToShow = this.products.slice()
            this.sortElements();
        } else {
            this.productsToShow = this.products.filter(el => el.name.startsWith($event.target.value))
            this.sortElements();
        }
    }
}