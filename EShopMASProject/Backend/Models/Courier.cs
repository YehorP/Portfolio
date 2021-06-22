using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class Courier
    {
        public int workerId { get; set; }
        public int numberOfDeliveredOrdersForMnth { get; set; }
        public Worker Worker { get; set; }

        public virtual ICollection<Order> Orders { get; set; }
    }
}
