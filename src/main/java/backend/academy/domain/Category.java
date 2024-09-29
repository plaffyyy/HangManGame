package backend.academy.domain;

import java.security.SecureRandom;
import java.util.Map;

public abstract sealed class Category permits City, Country, Math, Medicine, State {
    private final SecureRandom randomGenerator = new SecureRandom();

    public abstract Map<String, String> getData();

    public Map.Entry<String, String> getElement() {
        Map<String, String> data = getData();
        int index = randomGenerator.nextInt(data.size());
        return data.entrySet().stream().toList().get(index);
    }
}
