package fr.mrcubee.reflect;

public interface ConditionalRunnable extends Runnable {

    public default void run(boolean success) {
        if (success)
            run();
    }

    default public ConditionalRunnable otherwise(final Runnable runnable) {
        return new ConditionalRunnable() {

            @Override
            public void run(boolean success) {
                if (success)
                    ConditionalRunnable.this.run();
                else
                    runnable.run();
            }

            @Override
            public void run() {

            }

        };
    }

}
