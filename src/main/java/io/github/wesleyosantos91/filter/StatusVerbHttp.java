package io.github.wesleyosantos91.filter;

public enum StatusVerbHttp {

    POST( 201, "POST"),
    PUT( 200, "PUT"),
    GET( 200, "GET"),
    DELETE( 204, "DELETE");

    private Integer codeHttp;
    private String verbHttp;

    StatusVerbHttp(Integer codeHttp, String verbHttp) {
        this.codeHttp = codeHttp;
        this.verbHttp = verbHttp;
    }

    public static Integer getHttpCode(String verbHttp) {
        for (StatusVerbHttp status : StatusVerbHttp.values()) {
            if (status.verbHttp.equals(verbHttp)) {
                return status.codeHttp;
            }
        }
        throw new IllegalArgumentException("the given number doesn't match any Status.");
    }
}
