package com.elias.site_generation.adapter.theme.out.file;

import com.elias.site_generation.shared.file.FilePath;

public interface FileManagerPort {

    void write(FilePath path, byte[] file);

    byte[] read(FilePath path);
    boolean exists(FilePath path);

    void remove(FilePath path);

}
