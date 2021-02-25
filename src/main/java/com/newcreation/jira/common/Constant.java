package com.newcreation.jira.common;

/**
 * Constants for Spring Security authorities.
 */
public interface Constant {

    String SYS_ADMIN = "ROLE_SYS_ADMIN";
    String ADMIN = "ROLE_ADMIN";
    String USER = "ROLE_USER";
    String MANAGER = "ROLE_MANAGER";
    String INVESTOR = "ROLE_INVESTOR";
    String OTHER = "ROLE_OTHER";
    String THIRD_PARTY = "ROLE_THIRD_PARTY";
    String SITE_INSPECTOR = "ROLE_SITE_INSPECTOR";
    String VALUER = "ROLE_VALUER";
    String ANONYMOUS = "ROLE_ANONYMOUS";
    String OXANE_USER = "ROLE_OXANE";
    String CLIENT_ADMIN = "ROLE_CLIENT_ADMIN";

    String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";
    String SYSTEM_ACCOUNT = "system";
    String ANONYMOUS_USER = "anonymoususer";
    String DEFAULT_LANGUAGE = "en";

    String TRANSACTION_ID = "transactionId";
    String TRANSACTION_ID_PARAM_NAME = "t";
    String TRANSACTION_ID_FIELD = "TRANSACTION_ID";
    String PORTFOLIO_ID = "PORTFOLIO_ID";

    String ENTITY_ID = "ENTITY_ID";
    String TRANSACTION = "transaction";
    String TRANSACTION_NAME = "transactionName";
    String DESCRIPTION = "description";
    String DEAL_TYPE_NAME = "dealTypeName";
    String CREATED_TEMPLATE_PASSWORD = "zaKdfgKB3Jfy4";
    char UNDERSCORE = '_';
    String MAILTO = "mailTo";
    String SERIAL_NO = "serialNo";

    String ENTITY = "entity";
    String SOURCE = "source";
    String DEAL_STAGE = "dealStage";
    String FUND = "fund";

    String SESSION_ELEMENTS = "sessionElements";
    String SUCCESS = "success";
    String PROGRESS = "progress";
    String IN_PROGRESS = "IN_PROGRESS";
    String ERROR = "error";
    String VALUES = "values";
    String MESSAGE = "message";

    String TEMPLATE_ID = "templateId";
    String TRANSACTION_COMPANY_ID = "transactionCompanyId";
    String INVESTOR_GROUP_ID_KEY = "investorGroupId";
    String LAST_UPDATED_TIME = "lastUpdatedTime";
    String LAST_UPDATED_BY = "lastUpdatedBy";
    String ENTITY_FIELD_ID = "entityFieldId";

    String PORTFOLIO = "PORTFOLIO";
    String CALCULATED = "CALCULATED";
    String DESCRIPTIVE = "DESCRIPTIVE";
    String BAD_DEBT = "BAD_DEBT";
    String PERCENTAGE = "Percentage";

    String CONTENT_DISPOSITION = "Content-Disposition";

    String INITIAL_BUSINESS_PLAN = "Initial Business Plan";
    String ACTUALS = "Actuals";
    String RE_BUSINESS_PLAN = "Re-Business Plan";

    String BLANK = "";

    char COMMA = ',';
    char DOT = '.';

    String COMMA_STR = ",";

    String YES = "YES";
    String NO = "NO";

    String ARIAL = "Arial";

    String QUESTION = "Question";

    int MAXIMUM_FRACTION_DIGITS = 2;

    String NA = "NA";

    String sqm = "sqm";
    String sqft = "sqft";
    String acres = "acres";
    String hectares = "hectares";

    String DASHBOARD = "DASHBOARD";
    String SCENARIO = "SCENARIO";

    String SPRING_PROFILE_DEVELOPMENT = "dev";
    String SPRING_PROFILE_TEST = "test";
    String SPRING_PROFILE_PRODUCTION = "prod";
    // Spring profile used when deploying with Spring Cloud (used when deploying to
// CloudFoundry)
    String SPRING_PROFILE_CLOUD = "cloud";
    // Spring profile used when deploying to Heroku
    String SPRING_PROFILE_HEROKU = "heroku";
    // Spring profile used to disable swagger
    String SPRING_PROFILE_SWAGGER = "swagger";
    // Spring profile used to disable running liquibase
    String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";
    // Spring profile used when deploying to Kubernetes and OpenShift
    String SPRING_PROFILE_K8S = "k8s";

    int PASSWORD_MIN_LENGTH = 8;
    int PASSWORD_MAX_LENGTH = 100;

    String SCALAR = "SCALAR";
    String VECTOR = "VECTOR";

    String TRADE_STATUS = "TRADE_STATUS";
    String TRADE_DATE = "TRADE_DATE";

    String SPACE = " ";
}