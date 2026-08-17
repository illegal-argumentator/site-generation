package com.elias.site_generation.adapter.file.out;

import java.nio.file.Path;

public interface FileService {

    void write(String name, byte[] file);
    byte[] read(Path path);

}
