﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class OrderProduct
    {
        public int id { get; set; }
        public int orderId { get; set; }
        public int productId { get; set; }
        public int amount { get; set; }
        public virtual Product Product { get; set; }
        public virtual Order Order { get; set; }
    }
}
