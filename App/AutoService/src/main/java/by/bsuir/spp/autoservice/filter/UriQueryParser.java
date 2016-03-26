package by.bsuir.spp.autoservice.filter;

class UriQueryParser {
    private final static String CONTEXT_REGEX = ".*/";
    private final static String PARAM_REGEX = ".*";

    private UriQueryParser() {}

    static String takeCommandName(String uri, String commandSuffix) {
        String result = null;

        if (uri != null && commandSuffix != null) {
            if (uri.endsWith(commandSuffix)) {
                result = uri.replaceAll(CONTEXT_REGEX, "");
                result = result.replaceAll(commandSuffix + PARAM_REGEX, "").toUpperCase();
            }
        }

        return result;
    }
}
