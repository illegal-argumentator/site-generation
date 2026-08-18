package com.elias.site_generation.adapter.file.out;

import java.util.Map;

public interface ZipFilePort {

    byte[] update(byte[] source, Map<String, byte[]> files);

    Map<String, byte[]> extract(byte[] source);

}
