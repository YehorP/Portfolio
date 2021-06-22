using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class CodeFirstContext : DbContext
    {
        public DbSet<Shop> Shop { get; set; }
        public DbSet<ShopProduct> ShopProduct { get; set; }
        public DbSet<Product> Product { get; set; }
        public DbSet<Order> Order { get; set; }
        public DbSet<Review> Review { get; set; }
        public DbSet<Employment> Employment { get; set; }
        public DbSet<Person> Person { get; set; }
        public DbSet<Client> Client { get; set; }
        public DbSet<Worker> Worker { get; set; }
        public DbSet<Courier> Courier { get; set; }
        public DbSet<Consultant> Consultant { get; set; }
        public DbSet<StorageWorker> StorageWorker { get; set; }
        public CodeFirstContext(DbContextOptions<CodeFirstContext> options) : base(options) { }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Shop>(entity =>
            {
                entity.HasKey(shop => shop.shopId);

                entity.Property(shop => shop.adress).IsRequired();
                entity.Property(shop => shop.phoneNumber).IsRequired().HasMaxLength(15);
                entity.Property(shop => shop.maxWorkersNum).HasDefaultValue(10);
            });

            modelBuilder.Entity<ShopProduct>(entity =>
            {
                entity.HasKey(shopProduct => new { shopProduct.shopId, shopProduct.productId});

                entity.Property(shopProduct => shopProduct.amount).IsRequired();

                entity.HasOne(shopProduct => shopProduct.Shop)
                      .WithMany(shop => shop.Products)
                      .OnDelete(DeleteBehavior.ClientSetNull)
                      .HasForeignKey(shopProduct => shopProduct.shopId);

                entity.HasOne(shopProduct => shopProduct.Product)
                      .WithMany(product => product.Shops)
                      .OnDelete(DeleteBehavior.ClientSetNull)
                      .HasForeignKey(shopProduct => shopProduct.productId);
            });

            modelBuilder.Entity<Product>(entity =>
            {
                entity.HasKey(product => product.productId);

                entity.Property(product => product.name).IsRequired();
                entity.Property(product => product.description).HasMaxLength(200);
                entity.Property(product => product.pricePerOne).IsRequired();
                entity.Property(product => product.producer).IsRequired();
                entity.Property(product => product.image).IsRequired();
                entity.Property(product => product.productType).HasConversion<int>().IsRequired();
                entity.Property(product => product.digitalType).HasConversion<int>();

                var data = new List<Product>();
                data.Add(
                    new Product
                    {
                        productId = 1,
                        name = "Play Station 5",
                        description = "New generation gaming console from Sony",
                        pricePerOne = 2000,
                        productType = Enums.ProductTypeEnum.Equipment,
                        producer = "Sony",
                        image = "",
                        type = "Gaming console"
                    }
                );
                data.Add(new Product
                    {
                        productId = 2,
                        name = "Xbox Series X",
                        description = "New generation gaming console from Microsoft",
                        pricePerOne = 2200,
                        productType = Enums.ProductTypeEnum.Equipment,
                        producer = "Microsoft",
                        image = "",
                        type = "Gaming console"
                    }
                );
                data.Add(new Product
                    {
                        productId = 3,
                        name = "WHIRLPOOL WFC 3C33 F X",
                        description = "Easy in use and compact washing machine",
                        pricePerOne = 1399,
                        productType = Enums.ProductTypeEnum.Equipment,
                        producer = "Whirlpool",
                        image = "",
                        type = "Kitchen electronics"
                    }
                );
                data.Add(new Product
                    {
                        productId = 4,
                        name = "1 mounth of Xbox Gaming Pass",
                        description = "Subscription based game pass with 90+ games",
                        pricePerOne = 200,
                        productType = Enums.ProductTypeEnum.DigitalProduct,
                        producer = "Microsoft",
                        image = "",
                        period = 30,
                        digitalType = Enums.DigitalTypeEnum.Subscription
                    }
                );
                data.Add(new Product
                    {
                        productId = 5,
                        name = "Windows 10 Pro License",
                        description = "New Opearting System developed and supported by Microsoft",
                        pricePerOne = 100,
                        productType = Enums.ProductTypeEnum.DigitalProduct,
                        producer = "Microsoft",
                        image = "",
                        digitalType = Enums.DigitalTypeEnum.License
                    }
                );
                data.Add(new Product
                    {
                        productId = 6,
                        name = "15$ Steam Gift Card",
                        description = "Gift card for Steam",
                        pricePerOne = 61,
                        productType = Enums.ProductTypeEnum.DigitalProduct,
                        producer = "Valve",
                        image = "",
                        digitalType = Enums.DigitalTypeEnum.License
                    }
                );
                data.Add(
                   new Product
                   {
                       productId = 7,
                       name = "Smart Tv 5",
                       description = "New model of LCD TV developed by Samsung with integrated AI",
                       pricePerOne = 3000,
                       productType = Enums.ProductTypeEnum.Equipment,
                       producer = "Samsung",
                       image = "",
                       type = "TV"
                   }
                );
                data.Add(
                   new Product
                   {
                       productId = 8,
                       name = "Microsoft Surface Laptop 3 15",
                       description = "Samll and ergonomic laptop for work developed by Microsoft",
                       pricePerOne = 5000,
                       productType = Enums.ProductTypeEnum.Equipment,
                       producer = "Microsoft",
                       image = "",
                       type = "Laptop"
                   }
                );

                for (int index = 0; index < data.Count; index++)
                {
                    if(File.Exists($"ProductsImages/Product{data[index].productId}.jpg"))
                    {
                        byte[] imageBytes = File.ReadAllBytes($"ProductsImages/Product{data[index].productId}.jpg");
                        var imageUrl  = Convert.ToBase64String(imageBytes);
                        data[index].image = string.Format("data:image/jpg;base64,{0}", imageUrl);
                    } 
                    else if (File.Exists($"ProductsImages/Product{data[index].productId}.png"))
                    {
                        byte[] imageBytes = File.ReadAllBytes($"ProductsImages/Product{data[index].productId}.png");
                        var imageUrl = Convert.ToBase64String(imageBytes);
                        data[index].image = string.Format("data:image/jpg;base64,{0}", imageUrl);
                    }

                }

                entity.HasData(data);

            });

            modelBuilder.Entity<Review>(entity =>
            {
                entity.HasKey(review => review.reviewId);

                entity.Property(review => review.comment).HasMaxLength(200);
                entity.Property(review => review.mark).IsRequired();

                entity.HasOne(review => review.Product)
                      .WithMany(product => product.Reviews)
                      .OnDelete(DeleteBehavior.ClientSetNull)
                      .HasForeignKey(review => review.productId);

                entity.HasOne(review => review.Client)
                      .WithMany(client => client.Reviews)
                      .OnDelete(DeleteBehavior.ClientSetNull)
                      .HasForeignKey(review => review.clientId);
            });

            modelBuilder.Entity<Employment>(entity =>
            {
                entity.HasKey(employment => employment.employmentId);

                entity.Property(employment => employment.from).IsRequired();

                entity.HasOne(employment => employment.Shop)
                      .WithMany(shop => shop.Employments)
                      .OnDelete(DeleteBehavior.ClientSetNull)
                      .HasForeignKey(employment => employment.shopId);

                entity.HasOne(employment => employment.Worker)
                      .WithMany(worker => worker.Employemts)
                      .OnDelete(DeleteBehavior.ClientSetNull)
                      .HasForeignKey(employment => employment.workerId);
            });


            modelBuilder.Entity<Order>(entity =>
            {
                entity.HasKey(order => order.orderId);

                entity.Property(order => order.creationTime).IsRequired();
                entity.Property(order => order.deliveryType).IsRequired().HasConversion<int>();
                entity.Property(order => order.paymentType).IsRequired().HasConversion<int>();
                entity.Property(order => order.realizationState).IsRequired().HasConversion<int>();
                entity.Property(order => order.recipientName).IsRequired();
                entity.Property(order => order.recipientSurname).IsRequired();
                entity.Property(order => order.recipientPhoneNumber).IsRequired().HasMaxLength(15);
                entity.Property(order => order.recipientEmail).IsRequired().HasMaxLength(320);
                entity.Property(order => order.deliveryAddress).IsRequired();

                entity.HasOne(order => order.Client)
                      .WithMany(client => client.Orders)
                      .OnDelete(DeleteBehavior.ClientSetNull)
                      .HasForeignKey(order => order.clientId);

                entity.HasOne(order=>order.Courier)
                      .WithMany(courier => courier.Orders)
                      .OnDelete(DeleteBehavior.ClientSetNull)
                      .HasForeignKey(order => order.curierId);
            });

            modelBuilder.Entity<OrderProduct>(entity =>
            {
                entity.HasKey(orderProduct => orderProduct.id);

                entity.Property(orderProduct => orderProduct.amount).IsRequired();

                entity.HasOne(productOrder => productOrder.Product)
                      .WithMany(product => product.Orders)
                      .OnDelete(DeleteBehavior.ClientSetNull)
                      .HasForeignKey(productOrder => productOrder.productId);

                entity.HasOne(productOrder => productOrder.Order)
                      .WithMany(order => order.Products)
                      .OnDelete(DeleteBehavior.ClientSetNull)
                      .HasForeignKey(productOrder => productOrder.orderId);
            });


            modelBuilder.Entity<Person>(entity =>
            {
                entity.HasKey(person => person.personId);

                entity.Property(person => person.name).IsRequired();
                entity.Property(person => person.surname).IsRequired();
                entity.Property(person => person.phoneNumber).IsRequired().HasMaxLength(15);
                entity.Property(person => person.email).IsRequired().HasMaxLength(320);
                entity.Property(person => person.login).IsRequired();
                entity.Property(person => person.password).IsRequired();

                entity.HasOne(person => person.Client)
                     .WithOne(client => client.Person)
                     .OnDelete(DeleteBehavior.Cascade)
                     .HasForeignKey<Client>(client => client.personId);

                entity.HasOne(person => person.Worker)
                    .WithOne(worker => worker.Person)
                    .OnDelete(DeleteBehavior.Cascade)
                    .HasForeignKey<Worker>(worker => worker.personId);

                var data = new List<Person>();

                data.Add(new Person
                {
                    personId = 1,
                    name = "Jan",
                    surname = "Kowalski",
                    phoneNumber = "+48123678192",
                    login = "jan123",
                    password = "kowalski123",
                    email = "jankowalski@gmail.com"
                });

                entity.HasData(data);

            });

            modelBuilder.Entity<Client>(entity =>
            {
                entity.HasKey(client => client.clientId);

                entity.Property(client => client.discountSize).IsRequired();

                var data = new List<Client>();

                data.Add(new Models.Client
                {
                    clientId = 1,
                    personId = 1,
                    discountSize = 0
                });

                entity.HasData(data);

            });


            modelBuilder.Entity<Worker>(entity =>
            {
                entity.HasKey(worker => worker.workerId);

                entity.Property(worker => worker.birthDate).IsRequired();
                entity.Property(worker => worker.hourRate).IsRequired();
                entity.Property(worker => worker.workingHoursAmount).IsRequired();
                entity.Property(worker => worker.minHourRate).IsRequired().HasDefaultValue(5);

                entity.HasOne(worker => worker.Courier)
                      .WithOne(courier => courier.Worker)
                      .OnDelete(DeleteBehavior.Cascade)
                      .HasForeignKey<Courier>(courier => courier.workerId);

                entity.HasOne(worker => worker.Consultant)
                      .WithOne(consultant => consultant.Worker)
                      .OnDelete(DeleteBehavior.Cascade)
                      .HasForeignKey<Consultant>(consultant => consultant.workerId);

                entity.HasOne(worker => worker.StorageWorker)
                      .WithOne(storageWorker => storageWorker.Worker)
                      .OnDelete(DeleteBehavior.Cascade)
                      .HasForeignKey<StorageWorker>(storageWorker => storageWorker.workerId);
            });


            modelBuilder.Entity<Courier>(entity =>
            {
                entity.HasKey(courier => courier.workerId);

                entity.Property(courier => courier.numberOfDeliveredOrdersForMnth).IsRequired();

            });

            modelBuilder.Entity<Consultant>(entity =>
            {
                entity.HasKey(consultant => consultant.workerId);

                entity.Property(consultant => consultant.numberOfSoldProductsForMnth).IsRequired();

            });

            modelBuilder.Entity<StorageWorker>(entity =>
            {
                entity.HasKey(storageWorker => storageWorker.workerId);

                entity.Property(storageWorker => storageWorker.numberOfAcceptedProductsForMnth).IsRequired();

            });

            //finished Order,Shop,Product,ShopProduct,Review,Employment,Person,Client,Worker,Courier,Consultant,StorageWorker
            //todo fill entiities with data
        }
    }
}
