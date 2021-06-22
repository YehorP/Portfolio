using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class Client
    {

        public int clientId { get; set; }
        public int personId { get; set; }
        public double discountSize { get; set; }
        public virtual Person Person { get; set; }
        public virtual ICollection<Order> Orders { get; set; }
        public virtual ICollection<Review> Reviews { get; set; }
    }
}
