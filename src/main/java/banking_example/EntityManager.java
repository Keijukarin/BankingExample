package banking_example;


import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

public class BankingExample {
    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(getUser("Alex1", "Tartu", "Alex@yahoo.com"));
            session.save(getUser("Martin", "Tallinn", "Martin@yahoo.com"));
            session.save(getAccount("normal", BigDecimal.valueOf(2500.00), 1l));
            session.save(getAccount("normal", BigDecimal.valueOf(3000.00), 2l));
            User user = session.get(User.class, 2l);
            System.out.println(user);
            Transaction transaction = getUserTransaction();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HibernateUtil.shutdown();
    }
    private static User getUser(String username, String address, String email) {
        User alex = new User();
        alex.setName(username);
        alex.setAddress(address);
        alex.setEmail(email);
        return alex;

    }

    private static Transaction getUserTransaction() {
        Transaction transaction = new Transaction();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter receiver id: ");
        Transaction.setId(scanner.nextLong());
        System.out.println("Enter sender id: ");
        Transaction.setId(scanner.nextLong());
        System.out.println("Enter amount: ");
        Transaction.setAmount(scanner.nextBigDecimal());
        scanner.close();
        Transaction.setCreatedAt(LocalDateTime.now());
        return transaction;
    }
    private static Account getAccount(String type, BigDecimal balance, long userId) {
        Account account = new Account();
        account.setUserId(userId);
        account.setAccountType(type);
        account.setBalance(balance);
        return account;
    }
}
