package concept.com.labtech.networking;

/**
 * Created by alex on 1/8/15.
 */
public interface NetworkService
{
    public void get(String url);
    public void post(String url, String data);
}