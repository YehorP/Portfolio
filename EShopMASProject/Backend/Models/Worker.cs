using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class Worker
    {
        public int workerId { get; set; }
        public int personId { get; set; }
        public DateTime birthDate { get; set; }
        public int? age { get; set; }
        public double? networkWorkingExperience { get; set; }
        public double workingHoursAmount { get; set; }
        public double hourRate { get; set; }
        public double minHourRate { get; set; }
        public virtual Courier Courier { get; set; }
        public virtual Consultant Consultant { get; set; }
        public virtual StorageWorker StorageWorker { get; set; }
        public virtual Person Person { get; set; }
        public virtual ICollection<Employment> Employemts { get; set; }
    }
}
