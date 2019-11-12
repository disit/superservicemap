/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicemap;

/**
 *
 * @author disit
 */
public class WfsException extends Exception {
    
    public static final String MISSING_PARAMETER_VALUE = "MissingParameterValue";
    public static final String INVALID_PARAMETER_VALUE = "InvalidParameterValue";
    public static final String VERSION_NEGOTIATION_FAILED = "VersionNegotiationFailed";
    public static final String NO_APPLICABLE_CODE = "NoApplicableCode";
    public static final String OPERATION_NOT_SUPPORTED = "OperationNotSupported";
    public static final String NOT_FOUND = "NotFound";
    public static final String OPTION_NOT_SUPPORTED = "OptionNotSupported";
    public static final String OPERATION_PROCESSING_FAILED = "OperationProcessingFailed";

    String code;
    String locator;

    public WfsException(String code, String locator) {
      this.code = code;
      this.locator = locator;
    }

    public String getCode() {
      return code;
    }

    public String getLocator() {
      return locator;
    }
    
}
