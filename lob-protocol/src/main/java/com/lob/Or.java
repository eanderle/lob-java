package com.lob;

public class Or<T1, T2> {
    final T1 refA;
    final T2 refB;

    private Or(final T1 refA, final T2 refB) {
        if (refA == null && refB == null) {
            throw new NullPointerException("both references can't be null!");
        }
        if (refA != null && refB != null) {
            throw new IllegalStateException("both references can't be set!");
        }

        this.refA = refA;
        this.refB = refB;
    }

    public static <T1, T2> Or<T1, T2> typeA(final T1 ref) {
        return new Or<T1, T2>(ref, null);
    }

    public static <T1, T2> Or<T1, T2> typeB(final T2 ref) {
        return new Or<T1, T2>(null, ref);
    }

    public boolean isTypeA() {
        return this.refA != null;
    }

    public boolean isTypeB() {
        return this.refB != null;
    }

    public T1 getTypeA() {
        if (this.refA == null) {
            throw new IllegalStateException("called getTypeA on an Or of type B!");
        }
        return this.refA;
    }

    public T2 getTypeB() {
        if (this.refB == null) {
            throw new IllegalStateException("called getTypeB on an Or of type A!");
        }
        return this.refB;
    }
}
