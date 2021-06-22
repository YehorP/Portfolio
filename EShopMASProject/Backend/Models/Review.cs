using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class Review
    {
        public int reviewId { get; set; }
        public int clientId { get; set; }
        public int productId { get; set; }
        public double mark { get; set; }
        public string? comment { get; set; }
        public virtual Client Client { get; set; }
        public virtual Product Product { get; set; }
    }
}
