package tryjava;

import java.io.IOException;

public class RunShell {
    public static void main(String[] args) throws IOException {
        String[] cmd = {"sh", "ls.sh", ""};
        Process process = Runtime.getRuntime().exec(cmd);
    }
}
