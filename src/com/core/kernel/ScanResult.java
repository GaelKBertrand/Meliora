package com.core.kernel;

import org.json.JSONStringer;

public class ScanResult {
    public String toJSON() {
        JSONStringer stringer =
                new JSONStringer();
        stringer.object()
                .key("res")
                .value(12.0)
                .endObject();
        return stringer.toString();
    }

    public static ScanResult ofJSON(String json) {
        return null;
    }
}
