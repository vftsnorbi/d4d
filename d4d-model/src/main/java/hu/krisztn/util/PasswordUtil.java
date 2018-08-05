package hu.krisztn.util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.NonNull;

import java.nio.charset.Charset;

/**
 * Created by Norbert Kriszt (norbert.kriszt@gmail.com) on 2018. 08. 05..
 */
public class PasswordUtil {

    /**
     * Compute a hash of a password.
     * Password provided is wiped from the memory at the end of this method
     *
     * @param password Password to hash
     * @return the hash in format "$argon2i$v=19$m=128000,t=3,p=4$sfSe5MewORVlg8cDtxOTbg$uqWx4mZvLI092oJ8ZwAjAWU0rrBSDQkOezxAuvrE5dM"
     */
    public static String hash(@NonNull char[] password) {
        return hash(password, 20);
    }

    /**
     * Compute a hash of a password.
     * Password provided is wiped from the memory at the end of this method
     *
     * @param password Password to hash
     * @return the hash in format "$argon2i$v=19$m=128000,t=3,p=4$sfSe5MewORVlg8cDtxOTbg$uqWx4mZvLI092oJ8ZwAjAWU0rrBSDQkOezxAuvrE5dM"
     */
    public static String hash(@NonNull char[] password,
                              @NonNull Integer iterations) {
        return hash(password, iterations, 128000);
    }

    /**
     * Compute a hash of a password.
     * Password provided is wiped from the memory at the end of this method
     *
     * @param password Password to hash
     * @return the hash in format "$argon2i$v=19$m=128000,t=3,p=4$sfSe5MewORVlg8cDtxOTbg$uqWx4mZvLI092oJ8ZwAjAWU0rrBSDQkOezxAuvrE5dM"
     */
    public static String hash(@NonNull char[] password,
                              @NonNull Integer iterations,
                              @NonNull Integer memory) {
        return hash(password, iterations, memory, 4);
    }

    /**
     * Compute a hash of a password.
     * Password provided is wiped from the memory at the end of this method
     *
     * @param password Password to hash
     * @return the hash in format "$argon2i$v=19$m=128000,t=3,p=4$sfSe5MewORVlg8cDtxOTbg$uqWx4mZvLI092oJ8ZwAjAWU0rrBSDQkOezxAuvrE5dM"
     */
    public static String hash(@NonNull char[] password,
                              @NonNull Integer iterations,
                              @NonNull Integer memory, @NonNull
                                      Integer parallelThreads) {
        return hash(password, Charset.defaultCharset(), iterations, memory, parallelThreads);
    }

    /**
     * Compute a hash of a password.
     * Password provided is wiped from the memory at the end of this method
     *
     * @param password Password to hash
     * @param charset  Charset of the password
     * @return the hash in format "$argon2i$v=19$m=128000,t=3,p=4$sfSe5MewORVlg8cDtxOTbg$uqWx4mZvLI092oJ8ZwAjAWU0rrBSDQkOezxAuvrE5dM"
     */
    public static String hash(@NonNull char[] password,
                              @NonNull Charset charset,
                              @NonNull Integer iterations,
                              @NonNull Integer memory, @NonNull
                                      Integer parallelThreads) {
        String hash;
        Argon2 argon2Hasher = null;
        try {
            // Create instance
            argon2Hasher = createInstance();
            //Compute and return the hash
            hash = argon2Hasher.hash(iterations, memory, parallelThreads, password, charset);
        } finally {
            //Clean the password from the memory
            if (argon2Hasher != null) {
                argon2Hasher.wipeArray(password);
            }
        }
        return hash;
    }

    /**
     * Verifies a password against a hash
     * Password provided is wiped from the memory at the end of this method
     *
     * @param hash     Hash to verify
     * @param password Password to which hash must be verified against
     * @param charset  Charset of the password
     * @return True if the password matches the hash, false otherwise.
     */
    public static boolean verify(@NonNull String hash, @NonNull char[] password, @NonNull Charset charset) {
        Argon2 argon2Hasher = null;
        boolean isMatching;
        try {
            // Create instance
            argon2Hasher = createInstance();
            //Apply the verification (hash computation options are included in the hash itself)
            isMatching = argon2Hasher.verify(hash, password, charset);
        } finally {
            //Clean the password from the memory
            if (argon2Hasher != null) {
                argon2Hasher.wipeArray(password);
            }
        }
        return isMatching;
    }

    /**
     * Create and configure an Argon2 instance
     *
     * @return The Argon2 instance
     */
    private static Argon2 createInstance() {
        // Create and return the instance
        return Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
    }
}
