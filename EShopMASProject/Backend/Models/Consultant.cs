using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class Consultant
    {
        public int workerId { get; set; }
        public int numberOfSoldProductsForMnth { get; set; }
        public Worker Worker { get; set; }
    }
}
