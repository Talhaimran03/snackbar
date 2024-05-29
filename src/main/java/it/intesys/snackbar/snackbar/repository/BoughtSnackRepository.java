package it.intesys.snackbar.snackbar.repository;

import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class BoughtSnackRepository {
    Map<String, Integer> boughtSnack;

    public BoughtSnackRepository() {
        boughtSnack = new HashMap<>();

        boughtSnack.put("Mars", 4);
        boughtSnack.put("Twix", 2);
        boughtSnack.put("Kinder Delice", 3);
    }

    public Integer addBoughtSnack(String snack) {
        boughtSnack.put(snack, boughtSnack.get(snack)+1);
        return boughtSnack.get(snack);
    }

    public Map<String, Integer> getTopSnacks() {
        return boughtSnack.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        Map::putAll
                );
    }

}
