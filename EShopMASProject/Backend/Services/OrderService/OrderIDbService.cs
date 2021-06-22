using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.DTO_s.POST;
using WebApplication1.DTO_s.Responses;
using WebApplication1.Enums;
using WebApplication1.Models;

namespace WebApplication1.Services.OrderService
{
    public interface OrderIDbService
    {
        public Task<int> createOrder(OrderCreateRequest request);

        public void deleteCanceledOrder(int orderId);

        public void showOrdersListInPeriod(DateTime from, DateTime to);

        public void showNumberOfProductOrder(int productId);

        public void changeOrderStatus(int orderId, RealizationStateEnum newStatus);

        public Task<List<ShowClientOrdersResponse>> showClientOrders(int clientId);
    }
}
