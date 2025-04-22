package com.saichand.Design.Patterns.builderPattern;

public class UrlBuilder {
    public static class Builder{
        private String protocol;
        private String host;
        private String port;
        private String path;
        private String query;

        public Builder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }
        public Builder host(String host) {
            this.host = host;
            return this;
        }
        public Builder port(String port) {
            this.port = port;
            return this;
        }
        public Builder path(String path) {
            this.path = path;
            return this;
        }
        public Builder query(String query) {
            this.query = query;
            return this;
        }
        public UrlBuilder build(){
            return new UrlBuilder(this);
        }
    }
    public String protocol;
    public String host;
    public String port;
    public String path;
    public String query;

    public UrlBuilder(Builder builder){
        this.protocol = builder.protocol;
        this.host = builder.host;
        this.port = builder.port;
        this.path = builder.path;
        this.query = builder.query;

    }


}
