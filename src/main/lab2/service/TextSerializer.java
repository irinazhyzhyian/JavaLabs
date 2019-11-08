package main.lab2.service;

import main.lab2.model.Medicine;
import main.lab2.exception.ConvertException;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextSerializer {
    private final Integer FIELDS_COUNT = 4;
    private final String FIELDS_SEPARATOR = "---";

    private Object[] getMedicineFields(Medicine medicine) {
        return new Object[]{medicine.getName(), medicine.getForm(), medicine.getPrice(), medicine.getOverdueDay()};
    }

    public String serializeToString(Medicine medicine) throws ConvertException {
        try {
            Object[] fields = getMedicineFields(medicine);
            List<String> stringList = Arrays.stream(fields)
                    .map(Object::toString)
                    .collect(Collectors.toList());
             return String.join(FIELDS_SEPARATOR, stringList);
        } catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    public Medicine deserializeFromString(String string) throws ConvertException {
        try {
            Medicine obj= new  Medicine();
            String[] fields = string.split(FIELDS_SEPARATOR, FIELDS_COUNT);
            return new Medicine.Builder()
                               .setName(fields[0])
                               .setForm(fields[1])
                               .setPrice(Double.valueOf(fields[2]))
                               .setOverdueDay(LocalDate.parse(fields[3]))
                               .build();

        } catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    public void toFile(Medicine medicine, File file) {
        try(FileWriter writer = new FileWriter(file)) {
            writer.write(serializeToString(medicine));
            writer.flush();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

     public Medicine fromFile(File file) {

         try {
             BufferedReader br = new BufferedReader(new FileReader(file));
             int character;
             StringBuilder sb = new StringBuilder();
             while ((character = br.read()) != -1) {
                 char ch = (char) character;
                 if (character == '\n') break;
                 sb.append(ch);
             }
             return  deserializeFromString(sb.toString());

         } catch (IOException | ConvertException ex) {
             ex.getMessage();
         }
         return null;
     }

}

