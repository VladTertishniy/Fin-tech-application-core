package com.extrawest.core.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathUtil {
    public static final String LOANS_ROOT_PATH = "/loans";
    public static final String APPLICATION_FORMS_ROOT_PATH = "/applicationForms";
    public static final String CREATE_PATH = "/create";
    public static final String UPDATE_PATH = "/update/{id}";
    public static final String DELETE_PATH = "/delete/{id}";
    public static final String ACCEPT_PATH = "/accept/{id}";
    public static final String BANK_REQUEST_ID_KEY = "BANK_REQUEST_ID";
    public static final String APPLICATION_FORM_ID_KEY = "APPLICATION_FORM_ID";
}
