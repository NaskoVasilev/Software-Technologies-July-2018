using System.ComponentModel.DataAnnotations;

namespace CatShop.Models
{ 
    

    public class Cat
    {
        [Key]
        public int Id { get; set; }

        [Required]
        public string Name { get; set; }

        [Required]
        public string Nickname { get; set; }

        [Required]
        public double Price { get; set; }
    }
}
