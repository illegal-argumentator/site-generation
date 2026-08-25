package com.elias.site_generation.port.remote;

public interface RemoteCommandPort {

    void upload(String localPath, String remotePath);

    void delete(String remotePath);

    void execute(String command);

}
