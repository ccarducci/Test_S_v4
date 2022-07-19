package it.testsv4.testsv4.model.fabric;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LegalPersonBeneficiary {
    private String fiscalCode;
    private String legalRepresentativeFiscalCode;

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getLegalRepresentativeFiscalCode() {
        return legalRepresentativeFiscalCode;
    }

    public void setLegalRepresentativeFiscalCode(String legalRepresentativeFiscalCode) {
        this.legalRepresentativeFiscalCode = legalRepresentativeFiscalCode;
    }
}
