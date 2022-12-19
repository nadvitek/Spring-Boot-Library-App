package com.example.cv01.aspect;

import co.infinum.retromock.BodyFactory;
import com.sun.istack.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public final class ResourceBodyFactory implements BodyFactory {

    @Override
    public InputStream create(@NotNull String input) throws IOException {
        return new FileInputStream(Objects.requireNonNull
                (ResourceBodyFactory.class.getClassLoader().getResource(input)).getFile());
    }
}
