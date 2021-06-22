import { DigitalTypeEnum } from "../enums/DigitalTypeEnum";
import { ProductTypeEnum } from "../enums/ProductTypeEnum";

export interface Product {
    productId: number
    name: string
    description: string
    pricePerOne: number
    producer: string
    image: string
    period?: number
    digitalType: DigitalTypeEnum
    type: string
    productType: ProductTypeEnum
}