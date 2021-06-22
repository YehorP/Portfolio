using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.DTO_s.POST;
using WebApplication1.DTO_s.Responses;
using WebApplication1.Enums;
using WebApplication1.Exceptions;
using WebApplication1.Models;

namespace WebApplication1.Services.OrderService
{
    public class OrderMsSqlDbService : OrderIDbService
    {
        public CodeFirstContext dbContext;

        public OrderMsSqlDbService(CodeFirstContext dbContext)
        {
            this.dbContext = dbContext;
        }

        public async Task<int> createOrder(OrderCreateRequest request)
        {
            if(await dbContext.Client.Where(client => client.clientId == request.clientId).SingleOrDefaultAsync() != null)
            {
                var order = new Order
                {
                    creationTime = DateTime.Now,
                    recipientName = request.recipientName,
                    recipientEmail = request.recipientEmail,
                    recipientPhoneNumber = request.recipientPhoneNumber,
                    recipientSurname = request.recipientSurname,
                    deliveryAddress = request.deliveryAddress,
                    deliveryType = request.deliveryType,
                    paymentType = request.paymentType,
                    realizationState = RealizationStateEnum.NotAccepted,
                    clientId = request.clientId
                };

                if(order.paymentType == PaymentTypeEnum.OnlinePayment)
                {
                    order.realizationState = RealizationStateEnum.Accepted;
                }

                await dbContext.Order.AddAsync(order);
                order.Products = new List<OrderProduct>();
                foreach (OrderProductConnection orderProdcut in request.products)
                {
                    var product = await dbContext.Product.Where(product => product.productId == orderProdcut.productId).SingleOrDefaultAsync();
                    if(product != null)
                    {
                        order.Products.Add(new OrderProduct { orderId = order.orderId, productId = product.productId, amount = orderProdcut.amount});
                    } else
                    {
                        throw new ProductNotFoundException("no such user");
                    }
                }

                await dbContext.SaveChangesAsync();

                return order.orderId;
            } else
            {
                throw new UserNotFoundException("no such user");
            }
        }

        public async Task<List<ShowClientOrdersResponse>> showClientOrders(int clientId)
        {
            if(await dbContext.Client.Where(client => client.clientId == clientId).SingleOrDefaultAsync() != null)
                return await dbContext.Order
                    .Where(order => order.clientId == clientId)
                    .Include(order => order.Products)
                    .ThenInclude(orderProduct => orderProduct.Product)
                    .Select(order => new ShowClientOrdersResponse(order))
                    .ToListAsync();
            else
            {
                throw new UserNotFoundException("wrong id passed");
            }
        }

        public void changeOrderStatus(int orderId, RealizationStateEnum newStatus)
        {
            throw new NotImplementedException();
        }
        public void deleteCanceledOrder(int orderId)
        {
            throw new NotImplementedException();
        }


        public void showNumberOfProductOrder(int productId)
        {
            throw new NotImplementedException();
        }

        public void showOrdersListInPeriod(DateTime from, DateTime to)
        {
            throw new NotImplementedException();
        }
    }
}
