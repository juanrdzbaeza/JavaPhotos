package com.juanrdzbaeza.javaphotos;

import com.juanrdzbaeza.javaphotos.util.DatabaseConnection;

public class JavaPhotosLogic {


    private final DatabaseConnection databaseConnection;

    public JavaPhotosLogic(DatabaseConnection databaseConnection) {
        this.databaseConnection = new DatabaseConnection();
    }

}
