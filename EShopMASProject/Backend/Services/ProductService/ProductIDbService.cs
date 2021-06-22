using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Models;

namespace WebApplication1.Services.ProductService
{
    public interface ProductIDbService
    {

        public Task<List<Product>> getAllProducts();

        public Task<Product> getProduct(int productId);
    }
}
