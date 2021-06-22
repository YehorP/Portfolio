using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Enums;

namespace WebApplication1.Models
{
    public class Product
    {
        public int productId { get; set; }
        public string name { get; set; }
        public string description { get; set; }
        public double pricePerOne { get; set; }
        public string producer { get; set; }
        public string image { get; set; }
        public int? period { get; set; }// in days
        public DigitalTypeEnum? digitalType { get; set; }
        public string? type { get; set; }
        public ProductTypeEnum productType { get; set; }
        public virtual ICollection<ShopProduct> Shops { get; set; }
        public virtual ICollection<Review> Reviews { get; set; }
        public virtual ICollection<OrderProduct> Orders { get; set; }

    }
}
