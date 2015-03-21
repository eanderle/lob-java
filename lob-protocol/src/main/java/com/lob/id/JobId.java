package com.lob.id;

public class JobId extends LobId {
    private JobId(final Prefix prefix, final String id) {
        super(prefix, id);
    }

    public JobId parse(final String s) {
        return new JobId(Prefix.JOB, s);
    }
}
