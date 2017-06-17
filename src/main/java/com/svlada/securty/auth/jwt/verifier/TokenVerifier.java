package com.svlada.securty.auth.jwt.verifier;

/**
 * @author vladimir.stankovic
 *         <p>
 *         Aug 17, 2016
 */
public interface TokenVerifier {
    public boolean verify(String jti);
}
