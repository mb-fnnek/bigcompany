package com.fnnek.bigcompany.mapper;

import com.fnnek.bigcompany.entity.ClientEntity;
import com.fnnek.bigcompany.entity.CompanyEntity;
import com.fnnek.bigcompany.entity.OrderEntity;
import com.fnnek.bigcompany.model.Client;
import com.fnnek.bigcompany.model.Company;
import com.fnnek.bigcompany.model.Order;

public class Mapper {
    
    public static OrderEntity mapOrderEntity(Order order) {
        return new OrderEntity(order.name(), order.amount(), order.date(), mapClientEntity(order.client()), mapCompanyEntity(order.company()));
    }

    public static CompanyEntity mapCompanyEntity(Company company) {
        return new CompanyEntity(company.name());
    }

    public static ClientEntity mapClientEntity(Client client) {
        return new ClientEntity(client.name(), client.country());
    }
}
