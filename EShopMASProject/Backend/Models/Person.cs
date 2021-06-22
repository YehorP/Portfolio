using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class Person
    {
        public int personId { get; set; }
        public string name { get; set; }
        public string surname { get; set; }
        public string phoneNumber { get; set; }
        public string login { get; set; }
        public string password { get; set; }
        public string email { get; set; }
        public virtual Client Client { get; set; }
        public virtual Worker Worker { get; set; }
    }
}
