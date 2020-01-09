package ru.tri;

import javax.ws.rs.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/")
public class CacheService {

    private static Cache cache = new CacheImpl(100);
    private static AtomicInteger key = new AtomicInteger();

    @POST
    @Path("/cache/put")
    @Produces("application/json")
    public String doPut(@QueryParam("value") int value) {
        cache.put(key.incrementAndGet(), value, 5000);
        int size = cache.size();
        String pattern = "{ \"key\":\"%s\", \"value\":\"%s\", \"size\":\"%s\"}";
        return String.format(pattern, key, value, size);
    }

    @GET
    @Path("/cache/get")
    @Produces("application/json")
    public String getValue(@QueryParam("key") int key) {
        Object value = cache.get(key);
        int size = cache.size();
        String pattern = "{ \"key\":\"%s\", \"value\":\"%s\", \"size\":\"%s\"}";
        return String.format(pattern, key, value, size);
    }

    @GET
    @Path("/cache")
    @Produces("application/json")
    public String getAll() {
        Map cacheAll = cache.getAll(cache.getKeys());
        Set keys = cacheAll.keySet();
        Set value = cacheAll.entrySet();
        int size = cache.size();
        String pattern = "{ \"key\":\"%s\", \"value\":\"%s\", \"size\":\"%s\"}";
        return String.format(pattern, keys, value, size);
    }

    @POST
    @Path("/cache/clear")
    @Produces("text/plain")
    public String clearCache() {
        cache.clear();
        int size = cache.size();
        String pattern = "{ \"size\":\"%s\"}";
        return String.format(pattern, size);
    }
}
