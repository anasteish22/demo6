package by.anastasia.demo6.dao.mapper;

import java.sql.ResultSet;

public interface Mapper<R>{
    R mapper(ResultSet resultSet);
}
