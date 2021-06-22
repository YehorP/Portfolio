using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.DTO_s.POST;
using WebApplication1.Services.PersonClientService;

namespace WebApplication1.Controllers
{
    [Route("api/user")]
    [ApiController]
    public class PersonsClientsController : ControllerBase
    {
        private PersonClientIDbService personClientService;

        public PersonsClientsController(PersonClientIDbService personClientService)
        {
            this.personClientService = personClientService;
        }

        [HttpPost("login")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<IActionResult> login([FromBody] PersonClientLoginRequest request)
        {
            var result = await personClientService.login(request);
            if (result != null)
            {
                return Ok(result);
            }

            return BadRequest("Bad credetials was passed");
        }

    }
}
