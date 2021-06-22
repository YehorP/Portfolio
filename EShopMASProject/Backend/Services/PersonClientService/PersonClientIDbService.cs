using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.DTO_s.POST;
using WebApplication1.Models;

namespace WebApplication1.Services.PersonClientService
{
    public interface PersonClientIDbService
    {

        public Task<Person> login(PersonClientLoginRequest request);

    }
}
