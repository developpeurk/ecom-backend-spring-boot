package org.sid.lightecomv1;

import net.bytebuddy.utility.RandomString;
import org.sid.lightecomv1.dao.CategoryRepository;
import org.sid.lightecomv1.dao.ProductRepository;
import org.sid.lightecomv1.entities.Category;
import org.sid.lightecomv1.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


import java.util.Random;

@SpringBootApplication
public class LightEcomV1Application implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(LightEcomV1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);
        categoryRepository.save(new Category(null, "Computers", null, null, null));
        categoryRepository.save(new Category(null, "Printers", null, null, null));
        categoryRepository.save(new Category(null, "Smartphones", null, null, null));
        Random rnd = new Random();
        categoryRepository.findAll().forEach(c ->{
            for (int i = 0; i < 10; i++) {
                Product p = new Product();
                p.setName(RandomString.make(18));
                p.setCurrentPrice(100+rnd.nextInt(1000));
                p.setAvailable(rnd.nextBoolean());
                p.setPromotion(rnd.nextBoolean());
                p.setSelected(rnd.nextBoolean());
                p.setPhotoName("unknown.png");
                p.setCategory(c);
                productRepository.save(p);
                //Path path = Paths.get(System.getProperty("user.home") + "/ecom/products/" + p.getPhotoName());
                //System.out.println(path);
        }
    });


    }
}
