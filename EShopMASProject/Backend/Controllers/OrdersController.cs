using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.DTO_s.POST;
using WebApplication1.Models;
using WebApplication1.Services.OrderService;
using WebApplication1.Exceptions;

namespace WebApplication1.Controllers
{
    [Route("api/orders")]
    [ApiController]
    public class OrdersController : ControllerBase
    {

        private OrderIDbService ordersService;
        public OrdersController(OrderIDbService ordersService)
        {
            this.ordersService = ordersService;
        }

        [HttpPost("create")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<IActionResult> createOrder([FromBody] OrderCreateRequest request)
        {
            try {
                var orderId = await ordersService.createOrder(request);
                return Ok(orderId);
            }
            catch(UserNotFoundException e)
            {
                return BadRequest("Wrong order data was passed");
            }
        }

        [HttpGet("clientOrders/{clientId:int}")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<IActionResult> getClientOrders(int clientId)
        {
            try
            {
                return Ok(await ordersService.showClientOrders(clientId));
            } catch (UserNotFoundException e)
            {
                return BadRequest("Wrong clientId was passed");
            } catch(ProductNotFoundException e)
            {
                return BadRequest("Wrong productId was passed");
            }catch(Exception e)
            {
                return StatusCode(500, "internal error");
            }
        }
    }
}
