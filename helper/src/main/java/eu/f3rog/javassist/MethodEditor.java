package eu.f3rog.javassist;

import javassist.CannotCompileException;
import javassist.CtMethod;
import javassist.expr.ExprEditor;

/**
 * @author FrantisekGazo
 */
public abstract class MethodEditor extends ExprEditor {

    public void instrument(CtMethod method) throws CannotCompileException {
        method.instrument(this);
    }
}
