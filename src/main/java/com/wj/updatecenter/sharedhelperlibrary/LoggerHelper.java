package com.wj.updatecenter.sharedhelperlibrary;

public abstract class LoggerHelper {
    public static String RECEIVED_REQUEST_LOG_MESSAGE_TEMPLATE =
            "Received {} request for endpoint: {} with: [body: {}, path variables: {}]";
    public static String GET_REQUEST_LOG_MESSAGE_TEMPLATE =
            "Get {} resource: {}";
    public static String POST_REQUEST_LOG_MESSAGE_TEMPLATE =
            "Create {} resource: {}";
    public static String PUT_REQUEST_LOG_MESSAGE_TEMPLATE =
            "Update {} resource: {}";
    public static String PATCH_REQUEST_LOG_MESSAGE_TEMPLATE =
            "Partially update {} resource: {}";
    public static String DELETE_REQUEST_LOG_MESSAGE_TEMPLATE =
            "Delete {} resource: {}";
}
