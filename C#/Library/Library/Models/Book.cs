using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Library.Models
{
    public class Book
    {
        [Key]
        public int Id { get; set; }

        [Required]
        public string Title { get; set; }

        public string Discription { get; set; }

        public string UserId { get; set; }

        [Required]
        public ApplicationUser User { get; set; }




    }
}
