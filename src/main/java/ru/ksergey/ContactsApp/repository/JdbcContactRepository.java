package ru.ksergey.ContactsApp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ksergey.ContactsApp.model.Contact;

import java.util.List;

@Repository
public class JdbcContactRepository implements ContactRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Contact create(Contact contact) {
        String sql = "INSERT INTO contacts (id, name, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, contact.getId(), contact.getName(), contact.getEmail());
        return contact;
    }

    @Override
    public Contact read(Integer id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> Contact.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .build(),
                id);
    }

    @Override
    public List<Contact> readAll() {
        String sql = "SELECT * FROM contacts";
        return jdbcTemplate.query(sql, (rs, rowNum) -> Contact.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .build());
    }

    @Override
    public Contact update(Contact contact) {
        String sql = "UPDATE contacts SET name = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getId());
        return contact;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
