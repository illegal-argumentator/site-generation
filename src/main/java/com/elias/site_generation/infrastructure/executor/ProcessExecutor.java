package com.elias.site_generation.infrastructure.executor;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ProcessExecutor {

    public void execute(List<String> command) {
        ProcessBuilder pb = new ProcessBuilder(command).redirectErrorStream(true);

        try(Process process = pb.start()) {
            String output = new String(process.getInputStream().readAllBytes());

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IllegalStateException("Command failed: " + output);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to execute command", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Command execution interrupted", e);
        }
    }
}
