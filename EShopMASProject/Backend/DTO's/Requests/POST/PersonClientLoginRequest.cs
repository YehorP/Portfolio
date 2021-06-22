using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.DTO_s.POST
{
    public class PersonClientLoginRequest
    {
        [Required]
        [MinLength(5)]
        public string login { get; set; }
        [Required]
        [MinLength(5)]
        public string password { get; set; }
    }
}
