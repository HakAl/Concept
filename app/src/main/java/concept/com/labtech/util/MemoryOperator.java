package concept.com.labtech.util;

import java.util.HashMap;

public interface MemoryOperator
{
    public void clearAllTokens();

    public void removeTokens(String... tokenKeys);

    public void updateTokens(HashMap<String, String> tokens);

    public String getToken(String key);

    public boolean isTokenSetValid(String expireKey, String... nullKeys);

    public boolean isTokenActive(String expireToken);

    public void setBooleanToken(String token, boolean bool);

    public boolean getBooleanToken(String token);

    public void migrateTokens(String[] from, String[] to);
}
