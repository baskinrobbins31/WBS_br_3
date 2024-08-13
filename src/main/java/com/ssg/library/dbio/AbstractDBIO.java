package main.java.com.ssg.library.dbio;

import java.io.FileReader;
import java.util.Properties;

public abstract class AbstractDBIO {

    private void getDatabaseConnection() {

    }


    private void open() {

    }

    private void close() {

    }

    protected abstract void create(Object o);

    protected abstract Object read();

    protected abstract void update(Object o );

    protected abstract void delete(Object o);
}
