using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class Shop
    {
        public int shopId { get; set; }
        public string adress { get; set; }
        public string phoneNumber { get; set; }
        public int maxWorkersNum {get; set;}
        public virtual ICollection<ShopProduct> Products { get; set; }
        public virtual ICollection<Employment> Employments { get; set; }
    }
}
