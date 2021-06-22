using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Models;

namespace WebApplication1.DTO_s.Responses
{
    public class GetProductsResponse
    {
        public IEnumerable<Product> products { get; set; }
    }
}
