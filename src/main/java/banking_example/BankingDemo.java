package banking_example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankingDemo {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banking_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setAddress("123 Main St");

        entityManager.persist(user);


        Account account = new Account();
        account.setUser(user);
        account.setAccountType("Savings");
        account.setBalance(BigDecimal.valueOf(1000.00));


        entityManager.persist(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(500.00);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setAccount(account);

        entityManager.persist(transaction);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
