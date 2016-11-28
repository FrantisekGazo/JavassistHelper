package eu.f3rog.javassist;

import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public final class Utils {

    public static CtMethod findTargetMethod(CtClass ctClass, String targetMethod, CtClass... targetMethodParams) throws NotFoundException {
        CtMethod overriddenMethod = null;
        try {
            overriddenMethod = ctClass.getDeclaredMethod(targetMethod, targetMethodParams);
        } catch (Exception e) {
            for (CtMethod method : ctClass.getMethods()) {
                if (method.getName().equals(targetMethod) && equalArrays(method.getParameterTypes(), targetMethodParams)) {
                    overriddenMethod = method;
                    break;
                }
            }
        }
        if (overriddenMethod == null) {
            throw new NotFoundException(String.format("Class %s doesn't contain any method named %s", ctClass.getName(), targetMethod));
        }
        return overriddenMethod;
    }

    public static boolean equalArrays(CtClass[] lhs, CtClass[] rhs) {
        if (lhs == rhs) { // checks for same array reference
            return true;
        }

        if (lhs == null || rhs == null) { // checks for null arrays
            return false;
        }

        final int length = lhs.length;
        if (rhs.length != length) { // arrays should be of equal length
            return false;
        }

        for (int i = 0; i < length; i++) { // compare array values
            if (!lhs[i].equals(rhs[i])) {
                return false;
            }
        }

        return true;
    }

    public static void log(String msg, Object... args) {
        internalLog(msg, false, args);
    }

    public static void lognl(String msg, Object... args) {
        internalLog(msg, true, args);
    }

    private static void internalLog(String msg, boolean newLine, Object... args) {
        //System.out.printf(msg + (newLine ? "\n" : ""), args);
    }
}
