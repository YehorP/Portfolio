import { DeliveryTypeEnum } from "../enums/DeliveryTypeEnum";
import { PaymentTypeEnum } from "../enums/PaymentTypeEnum";
import { RealizationStateEnum } from "../enums/RealizationStateEnum";
import { Client } from "./Client.model";
import { Product } from "./Product";

export interface Order {
    orderId: number;
    clientId: number;
    curierId?: number;
    creationTime: Date;
    finishDate: Date;
    realizationState: RealizationStateEnum;
    recipientName: string;
    recipientSurname: string;
    recipientEmail: string;
    recipientPhoneNumber: string;
    deliveryAddress: string;
    paymentType: PaymentTypeEnum;
    deliveryType: DeliveryTypeEnum;
    client: Client;
    products: Product[];
}