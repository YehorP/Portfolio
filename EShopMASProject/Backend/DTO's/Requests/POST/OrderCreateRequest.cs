using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Enums;

namespace WebApplication1.DTO_s.POST
{
    public class OrderCreateRequest
    {
        [Required]
        public int clientId { get; set; }
        [Required]
        public string recipientName { get; set; }
        [Required]
        public string recipientSurname { get; set; }
        [Required]
        public string recipientEmail { get; set; }
        [Required]
        public string recipientPhoneNumber { get; set; }
        [Required]
        public string deliveryAddress { get; set; }
        [Required]
        public PaymentTypeEnum paymentType { get; set; }
        [Required]
        public DeliveryTypeEnum deliveryType { get; set; }
        [Required]
        public List<OrderProductConnection> products { get; set; }
    }


    public class OrderProductConnection
    {
        public int productId { get; set; }

        public int amount { get; set; }
    }
}
