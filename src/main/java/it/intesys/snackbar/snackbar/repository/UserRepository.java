package it.intesys.snackbar.snackbar.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<String> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add("Pietro");
        users.add("Talha");
        users.add("Anna");
    }

    public Boolean userExists(String user) {
        return users.contains(user);
    }

}
