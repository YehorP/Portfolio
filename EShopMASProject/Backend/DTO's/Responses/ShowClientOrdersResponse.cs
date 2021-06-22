using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Enums;
using WebApplication1.Models;

namespace WebApplication1.DTO_s.Responses
{
    public class ShowClientOrdersResponse
    {
        public int orderId { get; set; }
        public DateTime creationTime { get; set; }
        public DateTime? finishDate { get; set; }
        public RealizationStateEnum realizationState { get; set; }
        public string recipientName { get; set; }
        public string recipientSurname { get; set; }
        public string recipientEmail { get; set; }
        public string recipientPhoneNumber { get; set; }
        public string deliveryAddress { get; set; }
        public PaymentTypeEnum PaymentType { get; set; }
        public DeliveryTypeEnum deliveryType { get; set; }
        public ICollection<ShowClientOrdersResponseOrderProduct> Products { get; set; }

        public ShowClientOrdersResponse(Order order)
        {
            this.orderId = order.orderId;
            this.creationTime = order.creationTime;
            this.finishDate = order.finishDate;
            this.realizationState = order.realizationState;
            this.recipientName = order.recipientName;
            this.recipientSurname = order.recipientSurname;
            this.recipientEmail = order.recipientEmail;
            this.recipientPhoneNumber = order.recipientPhoneNumber;
            this.deliveryAddress = order.deliveryAddress;
            this.PaymentType = order.paymentType;
            this.deliveryType = order.deliveryType;
            this.Products = new List<ShowClientOrdersResponseOrderProduct>();
            foreach(OrderProduct orderProduct in order.Products)
            {
                this.Products.Add(new ShowClientOrdersResponseOrderProduct(orderProduct));
            }

        }
    }

    public class ShowClientOrdersResponseOrderProduct
    {
        public int id { get; set; }
        public int productId { get; set; }
        public int amount { get; set; }
        public ShowClientOrdersResponseProduct Product { get; set; }

        public ShowClientOrdersResponseOrderProduct(OrderProduct orderProduct)
        {
            this.id = orderProduct.id;
            this.amount = orderProduct.amount;
            this.productId = orderProduct.productId;
            this.Product = new ShowClientOrdersResponseProduct(orderProduct.Product);
        }
    }

    public class ShowClientOrdersResponseProduct
    {
        public int productId { get; set; }
        public string name { get; set; }
        public string description { get; set; }
        public double pricePerOne { get; set; }
        public string producer { get; set; }
        public ShowClientOrdersResponseProduct(Product product)
        {
            this.productId = product.productId;
            this.name = product.name;
            this.description = product.description;
            this.pricePerOne = product.pricePerOne;
            this.producer = product.producer;
        }
    }
}
