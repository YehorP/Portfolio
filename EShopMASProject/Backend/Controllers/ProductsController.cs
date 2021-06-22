using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.DTO_s.Responses;
using WebApplication1.Models;
using WebApplication1.Services.ProductService;

namespace WebApplication1.Controllers
{
    [Route("api/products")]
    [ApiController]
    public class ProductsController : ControllerBase
    {

        private ProductIDbService productService;

        public ProductsController(ProductIDbService productService)
        {
            this.productService = productService;
        }

        [HttpGet("{id:int}")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<IActionResult> getProduct(int id)
        {
            var product = await productService.getProduct(id);
            if (product != null)
                return Ok(product);
            return NotFound("wrong productId");
        }

        [HttpGet]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<IActionResult> getAllProducts()
        {
            return Ok(
                new GetProductsResponse
                {
                    products = await productService.getAllProducts()
                }
            );
            
        }
    }
}
