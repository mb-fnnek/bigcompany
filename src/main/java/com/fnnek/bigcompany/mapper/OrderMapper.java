package com.fnnek.bigcompany.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fnnek.bigcompany.model.Order;

public class OrderMapper implements RowMapper<Order>{

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Order(rs.getLong(1),
        rs.getString(2),
        null,
        null,
        null,
        null);
    }
    
}
