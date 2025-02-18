package depixelation.gwindlib.config;

import com.mojang.datafixers.util.Pair;

import java.util.ArrayList;
import java.util.List;

class ModConfigProvider implements SimpleConfig.DefaultConfig {

    private String configContents = "";

    public List<Pair> getConfigsList() {
        return configsList;
    }

    private final List<Pair> configsList = new ArrayList<>();

    public <T> void addKeyValuePair(String key, T value, String comment) {
        configsList.add(new Pair<>(key, value));
        configContents += key + "=" + value + " #default: "
                + value + " | " + comment + "\n";
    }

    @Override
    public String get(String namespace) {
        return configContents;
    }
}

