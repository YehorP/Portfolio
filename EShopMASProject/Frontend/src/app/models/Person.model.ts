import { Client } from "./Client.model";

export interface Person {
    personId: number,
    name: string,
    surname: string,
    phoneNumber: string,
    login: string,
    password: string,
    email: string,
    client: Client
}