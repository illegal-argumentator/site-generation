package com.elias.site_generation.adapter.file.out;

import com.elias.site_generation.shared.file.FilePath;

public interface FilePort {

    void write(FilePath path, byte[] file);
    byte[] read(FilePath path);
    boolean exists(FilePath path);

}
