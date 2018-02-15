package ncec.cfweb;

/**
 *
 * @author DantalioNxxi
 */
public class CatalogException extends Exception{

    public CatalogException() {
    }

    public CatalogException(String message) {
        super(message);
    }

    public CatalogException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatalogException(Throwable cause) {
        super(cause);
    }

    public CatalogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
