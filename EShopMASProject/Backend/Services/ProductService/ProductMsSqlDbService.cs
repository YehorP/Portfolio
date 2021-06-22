using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Models;

namespace WebApplication1.Services.ProductService
{
    public class ProductMsSqlDbService : ProductIDbService
    {
        public CodeFirstContext dbContext;

        public ProductMsSqlDbService(CodeFirstContext dbContext)
        {
            this.dbContext = dbContext;
        }

        public async Task<List<Product>> getAllProducts()
        {
            return await dbContext.Product.AsNoTracking().ToListAsync();
        }

        public async Task<Product> getProduct(int productId)
        {
            return await dbContext.Product.Where(el => el.productId == productId).SingleOrDefaultAsync();
        }
    }
}
