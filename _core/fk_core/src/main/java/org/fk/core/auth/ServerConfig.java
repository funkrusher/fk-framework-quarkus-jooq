package org.fk.core.auth;


import io.quarkus.vertx.http.HttpServerOptionsCustomizer;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.TrustOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.vertx.core.http.HttpServerOptions;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ServerConfig implements HttpServerOptionsCustomizer {

    private final Logger LOGGER;

    @Inject
    public ServerConfig(Logger logger) {
        LOGGER = logger;
    }

    @Override
    public void customizeHttpsServer(HttpServerOptions options) {

        LOGGER.info("STARTING ServerConfig");

        // Enable SSL and configure the server's key and certificate as well as client trust options
        options.setSsl(true)
            .setPort(8443)
            .setKeyCertOptions(loadServerCertificateFromDB())  // Server certificate and private key
            .setTrustOptions(loadClientTrustStoreFromDB())     // Truststore for client certificates
            .setClientAuth(ClientAuth.REQUIRED);

        HttpServerOptionsCustomizer.super.customizeHttpsServer(options);
    }

    private PemKeyCertOptions loadServerCertificateFromDB() {
        // Fetch certificate and key from database (assume they are stored as PEM encoded strings)
        String privateKey = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDQs3mWdVAeRfo2\n" +
            "weY0rO1QLJkU4OrylVr1Jz6yHj4DTYf4JCblTgh+Mn92m5ewDCQqUVPyHHaMMtgx\n" +
            "Acwf5yxvF2wpgLGnKXqldG3VvO99lQr9kK91+Njt8zokTg9TtSOfUCXfUEbS0bF4\n" +
            "gpzTRwxswa0lp6uWWxiXmVNdjmD33MNQ8gK+40VwwbF1nZK4cWnCct69ZzUSczok\n" +
            "ENTjpwIGJQ1HjkWPAXE62qB/Qi9Mc/OC8wp+AK5rQgLIuMKWx9PRycUf5BxxBKGz\n" +
            "feQux6RBALePF+tpQHVDSPBo1Dawv5xHKguoRa8CW2ASA3oMQwMm4Abi9j1xlkiL\n" +
            "MElhbnc5AgMBAAECggEAG31wiBWGyp4MEddjk8+taLCfL2Va/T4YH4+eTgb2wyec\n" +
            "v94/M1gefUT4ji57gghvYRWaWZtPMDNoT7g9Y3xnWXC74hGeFPc9PPsDlrJO13tg\n" +
            "2bqLqh3vsRQnNe4ETdHf0dXUT0B7O7y8RDPsJVZH+DFgtcOKsgDo0dsz5HBjLHKu\n" +
            "aiEygEtMRMYN9JOJVvMO/zSY5/Lj55Jf/lePlP9q28G7UEckfz1eAyAHlGRBfkfJ\n" +
            "i1G6tgtMGp7DTalr6+obwozGvKAE9mCNIoYe11cz7aLXjqvOo2E7Vg7/8ocVUyKc\n" +
            "pB5zxAPsYVbMxv1lXbsDJpv0qTugwZHkyG+Al/dz8wKBgQDuCForBEQl5X+UrfNa\n" +
            "BQEDGsb6oktHXnXvwstGgnehK2isRiwhgl85bpfVnqjO/a1Kud1AzVUOLwZCrRJO\n" +
            "zwSwHILHgeHKpnAU2pmnJ1AyUH9Mz8IX/8U2aiD3r4KhJ/UTc/vVNmXK5/1DuI4K\n" +
            "A1z7dQnwP+6I7dhO2rGEEMbVowKBgQDgdFTcthrnIyiHIpLe0OACtWJfP5sniD1M\n" +
            "uyucJx6vxuxjjjQEwGiXEOWjjoDLmYswz7/cSm7qvzIKYdKuR8ibEPh8Audjy5LF\n" +
            "P+CJFN6X42eYo4H5N5NGV6u676V7yXitbXmC1oi8qxwyvthm4eAtOE4vRl0CVtqv\n" +
            "pSD0gzN1cwKBgGuJinL0snUMgd8B13dL23Ezn8GLoMbVfiKbtopcVv+RRPDPoxBg\n" +
            "ffTMafwwecNKCAhCgBrOqSaHSCTHXAYOsOd/efX4T7HmD6zcNZ62vBx/EYP6OI3v\n" +
            "F4HF4K8Fs9Zq3uWy+IsS0FojBOCbeFA9xLw13A+WUoW6LocxdhFcIvhRAoGAfMDr\n" +
            "8gjdhx7JheK3k1SxTZAXby1hRWW5/I4DvTUZH0YHWYZOb12qRFzF5lRsTb232Zd+\n" +
            "4igU4jEG1TJt8SRA4b9a/UJ37cXWe//3AvowhmeEyxgGQ8iZ+Pz9nFdauSTQCETS\n" +
            "xuAK/CJ299WPXg+plDi5bBlF1OkFf57eZEkQLvUCgYBWh2L/dUoPz6j9Sn/nFayT\n" +
            "fnz2ew5nlMP0RgzsNkPYXrTKMnCKEX2bZ8kJeQmQvWy8hQrxRqxkYK0Q/GsL77L8\n" +
            "owuinFZW6yuAXS6WSia+0xrAJlcwhTCMc2mwMvIuQdyxRLpvaA2JprBfWNu8Dpiw\n" +
            "kH4HhipxeeG4cmFpQUCBaw==\n" +
            "-----END PRIVATE KEY-----\n";

        String certificate = "-----BEGIN CERTIFICATE-----\n" +
            "MIIDIDCCAggCFH4O0P2Siq6wyKZLSlJ1IpdfB21RMA0GCSqGSIb3DQEBCwUAMEwx\n" +
            "CzAJBgNVBAYTAklOMQswCQYDVQQIDAJUUzELMAkGA1UEBwwCVFMxFDASBgNVBAoM\n" +
            "C015Q0FDb21wYW55MQ0wCwYDVQQDDARNeUNBMB4XDTI0MTAwODE4NTQwOVoXDTI1\n" +
            "MTAwODE4NTQwOVowTTELMAkGA1UEBhMCSU4xCzAJBgNVBAgMAlRTMQswCQYDVQQH\n" +
            "DAJUUzERMA8GA1UECgwITXlDbGllbnQxETAPBgNVBAMMCE15Q2xpZW50MIIBIjAN\n" +
            "BgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0LN5lnVQHkX6NsHmNKztUCyZFODq\n" +
            "8pVa9Sc+sh4+A02H+CQm5U4IfjJ/dpuXsAwkKlFT8hx2jDLYMQHMH+csbxdsKYCx\n" +
            "pyl6pXRt1bzvfZUK/ZCvdfjY7fM6JE4PU7Ujn1Al31BG0tGxeIKc00cMbMGtJaer\n" +
            "llsYl5lTXY5g99zDUPICvuNFcMGxdZ2SuHFpwnLevWc1EnM6JBDU46cCBiUNR45F\n" +
            "jwFxOtqgf0IvTHPzgvMKfgCua0ICyLjClsfT0cnFH+QccQShs33kLsekQQC3jxfr\n" +
            "aUB1Q0jwaNQ2sL+cRyoLqEWvAltgEgN6DEMDJuAG4vY9cZZIizBJYW53OQIDAQAB\n" +
            "MA0GCSqGSIb3DQEBCwUAA4IBAQCUl2esyorbdkRp05otohZ0cMe7UVzbjQny/q0o\n" +
            "cS6KRRxZ1Dyh0hSG6YRPATi6zojhtfDhF/5zu3mY+PWYKwBj7fgx1W0PvyqE9L5w\n" +
            "zE7vJp12zOK4+6yMG1Vgv5dKDUKc0cbmueWNH6j2hpXBOatxYnYBMOMLdl0lLp+O\n" +
            "5eioaABiiR4DCyx04NmU56PF1+Aq07p1FGyrmtXR/iPdtIKvYMZOp6ClT29NXLWT\n" +
            "unQWjQ9AlEZ7TykA6mApoItuOkIiiqFezpothVyyFQAdBcL/fBPGRpQIX0gNaKxs\n" +
            "8ABvvkSZHxwU3SGkW7PT5JUcDVrfI3Fu4E3QICV+JLy3v/sm\n" +
            "-----END CERTIFICATE-----\n";

        return new PemKeyCertOptions()
            .setKeyValue(Buffer.buffer(privateKey))
            .setCertValue(Buffer.buffer(certificate));
    }

    private TrustOptions loadClientTrustStoreFromDB() {
        // Fetch the trusted CA certificates from the database (PEM format)
        String caCertificate = "-----BEGIN CERTIFICATE-----\n" +
            "MIIDeTCCAmGgAwIBAgIUJg5K3YXkCpOWwaQkC2DHCeikEXUwDQYJKoZIhvcNAQEL\n" +
            "BQAwTDELMAkGA1UEBhMCSU4xCzAJBgNVBAgMAlRTMQswCQYDVQQHDAJUUzEUMBIG\n" +
            "A1UECgwLTXlDQUNvbXBhbnkxDTALBgNVBAMMBE15Q0EwHhcNMjQxMDA4MTg1MzU2\n" +
            "WhcNMzQxMDA2MTg1MzU2WjBMMQswCQYDVQQGEwJJTjELMAkGA1UECAwCVFMxCzAJ\n" +
            "BgNVBAcMAlRTMRQwEgYDVQQKDAtNeUNBQ29tcGFueTENMAsGA1UEAwwETXlDQTCC\n" +
            "ASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAJatqNkm8DkHFJ7r3IGoxwLb\n" +
            "T7uVyVjixhf/Mv1+VVO5XNw9GMwuWobZFHqhSHqWX070MXWkgmbUik1vNaGc6s1M\n" +
            "9rvBFzhH183RSqSSCNhjU4wWKQt9Rh9dm8z9WFK0DoBNGUoZRkRstNnUBT2gGKZe\n" +
            "3N/oVkbGnJ/p0W4YcbUlzxzgB6d/SneIssBZpX1a7bMKVmreQnEhDymi2vK/j/5H\n" +
            "pq4tESR+0QvCMvhsQsl6Vp6QoLIYyUjOmsj7FJ1h4Foc5lQzPzKBZClnr3aqJoHv\n" +
            "H7nzgwZuOitY2102rxK36fTlkkBlf9M4UVXtrx1lshn/Z/OGD8uwnyulkHX1A08C\n" +
            "AwEAAaNTMFEwHQYDVR0OBBYEFHtEfuZL+xSy8DkzeYbuMhfbHH1KMB8GA1UdIwQY\n" +
            "MBaAFHtEfuZL+xSy8DkzeYbuMhfbHH1KMA8GA1UdEwEB/wQFMAMBAf8wDQYJKoZI\n" +
            "hvcNAQELBQADggEBADZNv4PamLo+C8OPP0q6dgVFn3G12XU1syxfiBpm1YhxC3h2\n" +
            "dSowfCwOX1VgGAPRlYMcTiWo1sntMwoFesyNpd4wdBygVMME2IM/BarTmAwLI7nB\n" +
            "D5jWW/kDmvrW4kjJxlIYTQf9u+OR/jFQNPBk9z596VvSMmQnTkBMfUZoRo/vh+eT\n" +
            "JvMkQ4OkcqfAANd7KmYs4fCtVBx06QIFLAvLc7/Xl1GTVmtFg5r95/BVtdNQDtRX\n" +
            "K6tFDJPmDoPEdoFM5llq1+L/S+LhmK9CBnuooCbDhipXaTZCn0uRIfq3ebvIAcKH\n" +
            "WkecHPFjhsa3JtnpZURphfeGbSiWklNVI/3p8bY=\n" +
            "-----END CERTIFICATE-----";

        return new PemTrustOptions()
            .addCertValue(Buffer.buffer(caCertificate));
    }

}