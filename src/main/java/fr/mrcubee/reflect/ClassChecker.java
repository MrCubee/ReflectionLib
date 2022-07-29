package fr.mrcubee.reflect;

import java.lang.reflect.Method;

public class ClassChecker {

    public static Class<?> getClass(final String className) {
        Class<?> clazz;

        if (className == null)
            return null;
        try {
            clazz = Class.forName(className);
        } catch (Exception ignored) {
            clazz = null;
        }
        return clazz;
    }

    public static boolean checkClass(final String className) {
        return getClass(className) != null;
    }

    public static boolean ifClassExist(final String className, ConditionalRunnable runnable) {
        final boolean exist = checkClass(className);

        if (runnable != null)
            runnable.run(exist);
        return exist;
    }

    public static Method getMethod(final Class<?> clazz, final String methodName, final Class<?>... parameterTypes) {
        Method method;

        if (clazz == null || methodName == null)
            return null;
        try {
            method = clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (Exception ignored) {
            method = null;
        }
        return method;
    }

    public static boolean checkMethod(final Class<?> clazz, final String methodName, final Class<?>... parameterTypes) {
        return getMethod(clazz, methodName, parameterTypes) != null;
    }

    public static boolean ifMethodExist(final ConditionalRunnable runnable, final Class<?> clazz, final String methodName, final Class<?>... parameterTypes) {
        final boolean exist = checkMethod(clazz, methodName, parameterTypes);

        if (runnable != null)
            runnable.run(exist);
        return exist;
    }

    public static boolean allCheck(final boolean... checks) {
        if (checks == null)
            return false;
        for (final boolean bool : checks) {
            if (!bool)
                return false;
        }
        return true;
    }

    public static boolean ifAllCheck(final ConditionalRunnable runnable, final boolean... checks) {
        final boolean success = allCheck(checks);

        if (runnable != null)
            runnable.run(success);
        return success;
    }

    public static boolean anyCheck(final boolean... checks) {
        if (checks == null)
            return false;
        for (final boolean bool : checks) {
            if (bool)
                return true;
        }
        return false;
    }

    public static boolean ifAnyCheck(final ConditionalRunnable runnable, final boolean... checks) {
        final boolean success = anyCheck(checks);

        if (runnable != null)
            runnable.run(success);
        return success;
    }

}
