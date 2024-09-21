package backend.academy.domain;

import java.util.Map;

public abstract sealed class Category permits City, Country, Math, Medicine, State {
    public abstract Map.Entry<String, String> getElement();
}
