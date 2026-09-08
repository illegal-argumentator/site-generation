package com.elias.site_generation.adapter.theme.out.generation.zip;

import java.util.Map;

public interface ZipFilePort {

    byte[] update(byte[] target, Map<String, byte[]> files);

    byte[] extract(String filename, byte[] source);

}
