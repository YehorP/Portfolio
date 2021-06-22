using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class StorageWorker
    {
        public int workerId { get; set; }
        public int numberOfAcceptedProductsForMnth { get; set; }
        public Worker Worker { get; set; }
    }
}
