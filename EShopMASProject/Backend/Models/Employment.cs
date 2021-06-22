using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class Employment
    {
        public int employmentId { get; set; }
        public int shopId { get; set; }
        public int workerId { get; set; }
        public virtual DateTime from { get; set; }
        public virtual DateTime? to { get; set; }
        public virtual Shop Shop { get; set; }
        public virtual Worker Worker {get; set;}
    }
}
