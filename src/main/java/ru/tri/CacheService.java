package ru.tri;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import java.util.Map;

@Path("/")
public class CacheService {

    private static CacheImpl cache = new CacheImpl(100);
    private ObjectMapper mapper = new ObjectMapper();

    @POST
    @Path("/cache/put")
    @Produces("application/json")
    public String doPut(@QueryParam("key") int key,
                        @QueryParam("value") int value) throws JsonProcessingException {
        cache.put(key, value);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.writeValueAsString(cache.getCache());
    }

    @GET
    @Path("/cache/get")
    @Produces("application/json")
    public String getValue(@QueryParam("key") int key) throws JsonProcessingException {
        Object value = cache.get(key);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.writeValueAsString(value);
    }

    @GET
    @Path("/cache")
    @Produces("application/json")
    public String getAll() throws JsonProcessingException {
        Map cacheAll = cache.getAll(cache.getKeys());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.writeValueAsString(cacheAll);
    }

    @POST
    @Path("/cache/clear")
    @Produces("text/plain")
    public String clearCache() throws JsonProcessingException {
        cache.clear();
        int size = cache.size();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.writeValueAsString(size);
    }
}
