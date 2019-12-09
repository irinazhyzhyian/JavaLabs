package lab2.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.IO;

import java.io.File;
import java.io.IOException;

public class JsonSerializer<T> implements IO<T> {
    private Class<T> classObject;

    public JsonSerializer(Class<T> type) {
        this.classObject =type;
    }

    /**
     *
     * @param obj
     * @param file
     * Convert object to JSON string and save into file directly
     */
    @Override
    public void toFile(T obj, File file) {

        try {
            ObjectMapper mapper= new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, obj);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            System.out.println("Exception Occurred while converting java obj into json" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param file
     * @return java object
     * read JSON string from file and convert string into java object
     */
    @Override
    public T fromFile(File file)  {
        try {
            ObjectMapper mapper= new ObjectMapper();
            return mapper.readValue(file, classObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param obj
     * @return object as jsonString
     * @throws JsonProcessingException
     */
    @Override
    public String serializeToString(T obj) throws JsonProcessingException {
        ObjectMapper mapper= new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    @Override
    public T deserializeFromString(String string) throws IOException {
        ObjectMapper mapper= new ObjectMapper();
        return mapper.readValue(string, classObject);
    }

}