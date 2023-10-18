package ru.kata.spring.bootstrap.demo.converter;

import ru.kata.spring.bootstrap.demo.model.Role;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorityDatabaseConverter implements AttributeConverter<List<Role>, String> {

    private static final String SPLIT_CHAR = ";";
    @Override
    public String convertToDatabaseColumn(List<Role> roles) {
        return roles.stream().map(Role::getRoleName).collect(Collectors.joining(SPLIT_CHAR));
    }

    @Override
    public List<Role> convertToEntityAttribute(String string) {
        return Arrays.stream(string.split("\\" + SPLIT_CHAR)).map(Role::new).toList();
    }
}
