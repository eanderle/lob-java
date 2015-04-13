package com.lob.protocol.request;

import com.lob.Or;

import java.io.File;

public class FileParam {
    private final String name;
    private final Or<File, String> fileOrUrl;

    public FileParam(final String name, final Or<File, String> fileOrUrl) {
        this.name = name;
        this.fileOrUrl = fileOrUrl;
    }

    public static FileParam file(final String name, final File file) {
        return new FileParam(name, Or.<File, String>typeA(file));
    }

    public static FileParam url(final String name, final String url) {
        return new FileParam(name, Or.<File, String>typeB(url));
    }

    public boolean isFile() {
        return this.fileOrUrl.isTypeA();
    }

    public boolean isUrl() {
        return this.fileOrUrl.isTypeB();
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return this.fileOrUrl.getTypeA();
    }

    public String getUrl() {
        return this.fileOrUrl.getTypeB();
    }

    @Override
    public String toString() {
        return "FileParam{" +
            "fileOrUrl=" + fileOrUrl +
            ", name='" + name + '\'' +
            '}';
    }
}
