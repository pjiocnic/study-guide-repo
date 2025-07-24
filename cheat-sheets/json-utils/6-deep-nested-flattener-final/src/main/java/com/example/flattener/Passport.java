package com.example.flattener;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Passport {
	
	@JsonProperty("N_UMBER") 
    private String N_UMBER;
	@JsonProperty("C_OUNTRY") 
    private String C_OUNTRY;
	@JsonProperty("E_XPIRY")
    private String E_XPIRY;

    public String getN_UMBER() { return N_UMBER; }
    public String getC_OUNTRY() { return C_OUNTRY; }
    public String getE_XPIRY() { return E_XPIRY; }
}
