using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.DTO_s.POST;
using WebApplication1.Models;

namespace WebApplication1.Services.PersonClientService
{
    public class PersonClientMsSqlDbService : PersonClientIDbService
    {
        public CodeFirstContext dbContext;

        public PersonClientMsSqlDbService(CodeFirstContext dbContext)
        {
            this.dbContext = dbContext;
        }

        public async Task<Person> login(PersonClientLoginRequest request)
        {
            return await dbContext.Person.Where(user => user.login == request.login && user.password == request.password).Include(user => user.Client).SingleOrDefaultAsync();
        }
    }
}
