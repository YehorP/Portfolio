import { DeliveryTypeEnum } from "src/app/enums/DeliveryTypeEnum";
import { PaymentTypeEnum } from "src/app/enums/PaymentTypeEnum";


export interface CreateOrderRequest {
    clientId: number;
    recipientName: string;
    recipientSurname: string;
    recipientEmail: string;
    recipientPhoneNumber: string;
    deliveryAddress: string;
    paymentType: PaymentTypeEnum;
    deliveryType: DeliveryTypeEnum;
    products: {productId: number, amount: number}[]
}