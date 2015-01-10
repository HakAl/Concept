package concept.com.labtech.util;

import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

public final class SharedMemoryFacade implements MemoryOperator
{
    private SimpleDateFormat dateFormatter;
    private SharedPreferences sharedPreferences;

    public SharedMemoryFacade(SharedPreferences sharedPreferences, SimpleDateFormat dateFormatter)
    {
        this.sharedPreferences = sharedPreferences;
        this.dateFormatter = dateFormatter;
    }

    public void removeTokens(String... tokenKeys)
    {
        for (String tokenKey : tokenKeys) {
            this.sharedPreferences.edit().remove(tokenKey).commit();
        }
    }

    public void clearAllTokens()
    {
        this.sharedPreferences.edit().clear().commit();
    }

    public void updateTokens(HashMap<String, String> tokens)
    {
        for (Entry<String, String> tokenMap : tokens.entrySet()) {
            this.sharedPreferences.edit().putString(tokenMap.getKey(), tokenMap.getValue()).commit();
        }
    }

    public String getToken(String key)
    {
        return this.sharedPreferences.getString(key, null);
    }

    public boolean isTokenSetValid(String expireKey, String... nullKeys)
    {
        for (String key : nullKeys) {
            if (this.sharedPreferences.getString(key, null) == null) {
                return false;
            }
        }
        return isTokenActive(this.sharedPreferences.getString(expireKey, null));
    }

    public boolean isTokenActive(String expireToken)
    {
        boolean tokenActive = false;
        if (expireToken != null) {
            try {
                Date expires = this.dateFormatter.parse(expireToken);
                long expirationTime = expires.getTime();
                long currentTime = System.currentTimeMillis();

                if (currentTime < expirationTime) {
                    tokenActive = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return tokenActive;
    }

    public void setBooleanToken(String token, boolean bool)
    {
        this.sharedPreferences.edit().putBoolean(token, bool).commit();
    }

    public boolean getBooleanToken(String token)
    {
        return this.sharedPreferences.getBoolean(token, false);
    }

    public void migrateTokens(String[] from, String[] to)
    {
        HashMap<String, String> newValues = new HashMap<>();

        for (int i = 0; i < from.length; i++) {
            newValues.put(to[i], this.getToken(from[i]));
        }
        this.updateTokens(newValues);
        this.removeTokens(from);
    }
}
