using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Enums;

namespace WebApplication1.Models
{
    public class Order
    {
        public int orderId { get; set; }
        public int clientId { get; set; }
        public int? curierId {get; set;}
        public DateTime creationTime { get; set; }
        public DateTime? finishDate { get; set; }
        public RealizationStateEnum realizationState { get; set; }
        public string recipientName { get; set; }
        public string recipientSurname { get; set; }
        public string recipientEmail { get; set; }
        public string recipientPhoneNumber { get; set; }
        public string deliveryAddress { get; set; }
        public PaymentTypeEnum paymentType { get; set; }
        public DeliveryTypeEnum deliveryType { get; set; }
        public virtual Client Client { get; set; }
        public virtual Courier Courier {get; set;}
        public virtual ICollection<OrderProduct> Products { get; set; }

    }
}
