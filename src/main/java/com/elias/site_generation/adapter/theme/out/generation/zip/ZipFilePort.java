package com.elias.site_generation.adapter.theme.out.generation.zip;

import com.elias.site_generation.shared.file.FilePath;

import java.util.Map;

public interface ZipFilePort {

    byte[] update(byte[] target, Map<FilePath, byte[]> files);

    byte[] extract(String filename, byte[] source);

}
