package ru.tri;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class CacheService {

    private static Cache cache = new CacheImpl(100);

    @POST
    @Path("/cache/put")
    @Produces("application/json")
    public String doPut(@QueryParam("key") int key,
                        @QueryParam("value") int value) throws JsonProcessingException {
        cache.put(key, value);
        HashMap<Integer, Integer> tmp = new HashMap<>();
        tmp.put(key, value);
        return new ObjectMapper().writeValueAsString(tmp);
    }

    @GET
    @Path("/cache/get")
    @Produces("application/json")
    public String getValue(@QueryParam("key") int key) throws JsonProcessingException {
        Object value = cache.get(key);
        return new ObjectMapper().writeValueAsString(value);
    }

    @GET
    @Path("/cache")
    @Produces("application/json")
    public String getAll() throws JsonProcessingException {
        Map cacheAll = cache.getAll(cache.getKeys());
        return new ObjectMapper().writeValueAsString(cacheAll);
    }

    @POST
    @Path("/cache/clear")
    @Produces("text/plain")
    public String clearCache() throws JsonProcessingException {
        cache.clear();
        int size = cache.size();
        return new ObjectMapper().writeValueAsString(size);
    }
}
