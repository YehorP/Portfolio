﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Exceptions
{
    public class UserNotFoundException: Exception
    {
      public UserNotFoundException(string message): base(message) { }
    }
}
