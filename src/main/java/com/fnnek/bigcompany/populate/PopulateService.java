package com.fnnek.bigcompany.populate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.fnnek.bigcompany.entity.ClientEntity;
import com.fnnek.bigcompany.entity.CompanyEntity;
import com.fnnek.bigcompany.entity.OrderEntity;
import com.fnnek.bigcompany.repository.ClientRepository;
import com.fnnek.bigcompany.repository.CompanyRepository;
import com.fnnek.bigcompany.repository.OrderRepository;
import com.google.common.collect.Lists;

@Service
public class PopulateService {

    private static final List<String> counries = List.of("PL", "EN", "DE", "FR", "ES", "UA", "CZ");
    
    private final CompanyRepository companyRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    public PopulateService(CompanyRepository companyRepository, ClientRepository clientRepository, OrderRepository orderRepository) {
        this.companyRepository = companyRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    public void populateClientsAndCompanies() {
        try (BufferedReader companyReader = new BufferedReader(new FileReader("src/main/resources/companies"))) {
            String currentLine;
            while ((currentLine = companyReader.readLine()) != null) {
                CompanyEntity entity = new CompanyEntity(currentLine.trim());
                System.out.println("Company: " + currentLine.trim());
                companyRepository.save(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader clientReader = new BufferedReader(new FileReader("src/main/resources/clients"))) {
            String currentLine;
            Random random = new Random();
            while ((currentLine = clientReader.readLine()) != null) {
                String country = counries.get(random.nextInt(counries.size() - 1));
                ClientEntity entity = new ClientEntity();
                entity.setCountry(country);
                entity.setName(currentLine.trim());
                System.out.println("Client: " + currentLine.trim() + ", " + country);
                clientRepository.save(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populateOrders() {
        List<String> words = new ArrayList<>();
        try (BufferedReader ordersReader = new BufferedReader(new FileReader("src/main/resources/words"))) {
            String currentLine;
            while ((currentLine = ordersReader.readLine()) != null) {
                String word = currentLine.trim();
                words.add(word);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<CompanyEntity> companies = companyRepository.findAll();
        List<ClientEntity> clients = clientRepository.findAll();

        Random random = new Random();

        List<OrderEntity> orders = new ArrayList<>();
        for (long i = 0; i < 50000000; i++) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setName(words.get(random.nextInt(words.size() - 1)));
            orderEntity.setClient(clients.get(random.nextInt(clients.size())));
            orderEntity.setCompany(companies.get(random.nextInt(companies.size())));
            BigDecimal amount = new BigDecimal(random.nextDouble(1000));
            amount = amount.setScale(2, RoundingMode.DOWN);
            orderEntity.setAmount(amount);
            orderEntity.setDate(randomDate());
            System.out.println("i: " + i);
            orders.add(orderEntity);
            //orderRepository.save(orderEntity);
        }

        List<List<OrderEntity>> parted = Lists.partition(orders, 50000);
        ExecutorService executor = Executors.newFixedThreadPool(20);

        parted.forEach(orderList -> executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Saving orders: " + Thread.currentThread().getName());
                orderRepository.saveAll(orderList);
                System.out.println("Finished saving orders: " + Thread.currentThread().getName());
            }
        }));
        System.out.println("Finished");
    }

    private LocalDateTime randomDate() {
        long minDay = LocalDate.of(2020, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2023, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

    return randomDate.atStartOfDay();
}
}
