using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.EntityFrameworkCore;

namespace TODOList.Models
{
    public class TaskDbContext:DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseMySQL("server=localhost;database=todo;user=root;SslMode=none;");
        }

        public DbSet<Task> Tasks { get; set; }

        public TaskDbContext()
        {
            this.Database.EnsureCreated();
        }

       
    }
}
