package main.lab5.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DataBaseBuilder {
    private static final String CREATE_PERSONS = "CREATE TABLE persons (id SERIAL NOT NULL PRIMARY KEY,first_name VARCHAR(50),last_name VARCHAR(50),birthday DATE,salary DOUBLE PRECISION)";
    private static final String DROP_PERSONS = "DROP TABLE persons";

    private static final String CREATE_MEDICINES = "CREATE TABLE medicines (id SERIAL NOT NULL PRIMARY KEY,name VARCHAR(20),form VARCHAR(20),overdue_day DATE,price DOUBLE PRECISION)";
    private static final String DROP_MEDICINES = "DROP TABLE medicines";

    private static final String CREATE_COUNT_MEDICINE = "CREATE TABLE count_medicines (id SERIAL NOT NULL PRIMARY KEY,medicine_id INTEGER NOT NULL REFERENCES medicines(id) ON DELETE CASCADE,count INTEGER)";
    private static final String DROP_COUNT_MEDICINE = "DROP TABLE count_medicines";

    private static final String CREATE_PHARMACY = "CREATE TABLE pharmacy (id SERIAL NOT NULL PRIMARY KEY,name VARCHAR(20),count_medicine_id INTEGER NOT NULL REFERENCES count_medicines(id) ON DELETE CASCADE,pharmacist_id INTEGER NOT NULL REFERENCES persons(id))";
    private static final String DROP_PHARMACY = "DROP TABLE pharmacy";

    private static Connection connection;


    /**
     * Creating table persons in Pharmacy DB
     *
     * @throws Exception if smth going wrong
     */
    public static void createPersonsTable() throws Exception {

        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_PERSONS);
            statement.executeUpdate();
        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            System.out.println("Table created!");
            connection.close();
        }
    }

    /**
     * Creating medicines table
     *
     * @throws Exception
     */
    public static void createMedicinesTable() throws Exception {

        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_MEDICINES);
            statement.executeUpdate();
        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            System.out.println("Table created!");
            connection.close();
        }
    }

    /**
     * Creating count_medicine table
     *
     * @throws Exception
     */
    public static void createCountMedicineTable() throws Exception {

        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_COUNT_MEDICINE);
            statement.executeUpdate();
        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            System.out.println("Table created!");
            connection.close();
        }
    }

    /**
     * Creating pharmacy table
     *
     * @throws Exception
     */
    public static void createPharmacyTable() throws Exception {

        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_PHARMACY);
            statement.executeUpdate();
        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            System.out.println("Table created!");
            connection.close();
        }
    }

    /**
     * Dropping table persons in Pharmacy DB
     *
     * @throws Exception if smth going wrong
     */
    public static void dropPersonsTable() throws Exception {

        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(DROP_PERSONS);
            statement.execute();
        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            System.out.println("Table dropped!");
            connection.close();
        }
    }

    /**
     * Dropping medicines table
     *
     * @throws Exception
     */
    public static void dropMedicinesTable() throws Exception {

        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(DROP_MEDICINES);
            statement.execute();
        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            System.out.println("Table dropped!");
            connection.close();
        }
    }

    /**
     * Dropping count_medicine table
     *
     * @throws Exception
     */
    public static void dropCountMedicineTable() throws Exception {

        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(DROP_COUNT_MEDICINE);
            statement.execute();
        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            System.out.println("Table dropped!");
            connection.close();
        }
    }

    /**
     * Drop pharmacy table
     *
     * @throws Exception
     */
    public static void dropPharmacyTable() throws Exception {

        try{
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(DROP_PHARMACY);
            statement.execute();
        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            System.out.println("Table dropped!");
            connection.close();
        }
    }
}
