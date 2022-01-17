package com.neo.identity.constant;

public interface IdentityTagName {

    interface POLICY{
        String ATTRIBUTE_DATA_TYPE = "ax2267:attributeDataType";
        String ATTRIBUTE_ID = "ax2267:attributeId";
        String ATTRIBUTE_VALUE = "ax2267:attributeValue";
        String CATEGORY = "ax2267:category";
    }

    interface SERVICE{
        String APPLICATION_NAME = "ax2169:applicationName";
        String DESCRIPTION = "/ns:return/ax2169:description";
        String CALL_BACK_URL = "ax2327:callbackUrl";
    }

}
